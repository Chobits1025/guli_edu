package com.guli.guli_teacher.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_teacher.entity.EduTeacher;
import com.guli.guli_teacher.mapper.EduTeacherMapper;
import com.guli.guli_teacher.query.TeacherQuery;
import com.guli.guli_teacher.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    @Autowired
    private EduTeacherMapper eduTeacherMapper;

    /**
     * 根据查询条件分页查询讲师信息
     * @param teacherPage
     * @param teacherQuery
     * @return
     */
    @Override
    public IPage<EduTeacher> pageQuery(Page<EduTeacher> teacherPage, TeacherQuery teacherQuery) {
        if(teacherQuery==null){
            return super.page(teacherPage,null);
        }

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();

        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        IPage<EduTeacher> page = super.page(teacherPage, wrapper);
        return page;
    }

    /**
     * 初始化首页讲师信息
     * @return
     */
    @Override
    public List<EduTeacher> initIndexTeacher() {
        return eduTeacherMapper.initIndexTeacher();
    }
}
