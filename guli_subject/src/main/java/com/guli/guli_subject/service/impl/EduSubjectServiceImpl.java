package com.guli.guli_subject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.guli_subject.entity.EduSubject;
import com.guli.guli_subject.mapper.EduSubjectMapper;
import com.guli.guli_subject.service.EduSubjectService;
import com.guli.guli_subject.vo.SubjectQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-27
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importSubject(MultipartFile file) {
        ArrayList<String> errorMsgs = new ArrayList<>();
        Workbook workbook = null;
        String fileName = file.getOriginalFilename();
        try {
            if (fileName.endsWith(".xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            } else if (fileName.endsWith(".xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else {
                errorMsgs.add("文件格式不正确");
                return errorMsgs;
            }

            log.info("获取Sheet页");
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            log.info("根据文件行数判断文件内容是否为空，该文件共{}行", lastRowNum);
            if (lastRowNum <= 1) {
                errorMsgs.add("选择的文件内容为空");
                return errorMsgs;
            }

            log.info("获取所有行，遍历");
            for (int rowNum = 1; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                Cell cellOne = row.getCell(0);
                if (cellOne == null) {
                    errorMsgs.add(rowNum + "行，第一列为空,导入失败");
                    continue;
                }
                String cellOneValue = cellOne.getStringCellValue();
                if (StringUtils.isEmpty(cellOneValue)) {
                    errorMsgs.add(rowNum + "行，第一列为空,导入失败");
                    continue;
                }

                Cell cellTwo = row.getCell(1);
                if (cellTwo == null) {
                    errorMsgs.add(rowNum + "行，第二列为空,导入失败");
                    continue;
                }
                String cellTwoValue = cellTwo.getStringCellValue();
                if (StringUtils.isEmpty(cellTwoValue)) {
                    errorMsgs.add(rowNum + "行，第二列为空,导入失败");
                    continue;
                }

                String pid;
                EduSubject subjectExists = this.getSubjectByTitle(cellOneValue);
                if (subjectExists == null) {
                    EduSubject subject = new EduSubject();
                    subject.setTitle(cellOneValue);
                    subject.setParentId("0");
                    baseMapper.insert(subject);
                    pid = subject.getId();
                    log.info(cellOneValue + "分类不存在，新增后获取回显的分类ID{}作为二级分类的parent ID", pid);
                } else {
                    pid = subjectExists.getId();
                    log.info(cellOneValue + "分类已存在，查询分类ID{}作为二级分类的parent ID", pid);
                }

                //判断二级分类是否存在
                EduSubject eduSubject = new EduSubject();
                eduSubject.setParentId(pid);
                eduSubject.setTitle(cellTwoValue);

                if (this.subjectExists(eduSubject)) {
                    errorMsgs.add("该分类" + eduSubject.getTitle() + "已存在，无需导入");
                } else {
                    baseMapper.insert(eduSubject);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return errorMsgs;
    }

    @Override
    public List<SubjectQueryVO> list() {
        //查询所有一级分类
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        List<EduSubject> eduSubjectsLvOne = baseMapper.selectList(wrapper);

        List<SubjectQueryVO> resultList = new ArrayList<>();
        if (eduSubjectsLvOne != null && eduSubjectsLvOne.size() > 0) {
            for (EduSubject eduSubject : eduSubjectsLvOne) {
                SubjectQueryVO subjectQueryVO = new SubjectQueryVO();
                BeanUtils.copyProperties(eduSubject, subjectQueryVO);
                log.info("属性对拷到QueryVo中,SubjectQueryVO:{}", subjectQueryVO);

                //查询是否有二级分类
                List<EduSubject> eduSubjectsLvTwo = baseMapper.selectList(new QueryWrapper<EduSubject>().eq("parent_id", eduSubject.getId()));

                if (eduSubjectsLvTwo != null && eduSubjectsLvTwo.size() > 0) {
                    subjectQueryVO.setChildren(eduSubjectsLvTwo);
                    log.info("存在二级分类subject:{}", eduSubjectsLvTwo);
                }

                resultList.add(subjectQueryVO);

            }
        }
        return resultList;
    }

    @Override
    public boolean existsSubjectLvOne(EduSubject eduSubject) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", eduSubject.getTitle());
        wrapper.eq("parent_id", "0");
        EduSubject subject = baseMapper.selectOne(wrapper);
        return subject != null;
    }

    @Override
    public List<EduSubject> listByParentId(String s) {
        QueryWrapper<EduSubject> qw = new QueryWrapper<EduSubject>().eq("parent_id", s);
        return list(qw);
    }

    public boolean subjectExists(EduSubject subject) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", subject.getTitle());
        wrapper.eq("parent_id", subject.getParentId());
        List<EduSubject> eduSubjects = this.baseMapper.selectList(wrapper);


        return eduSubjects != null && eduSubjects.size() > 0;
    }

    public EduSubject getSubjectByTitle(String title) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", title);
        wrapper.eq("parent_id", "0");

        return baseMapper.selectOne(wrapper);
    }
}
