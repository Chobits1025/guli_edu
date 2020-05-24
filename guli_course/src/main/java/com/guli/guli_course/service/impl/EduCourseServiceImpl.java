package com.guli.guli_course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.guli_commom.exception.EduCourseException;
import com.guli.guli_commom.vo.BosCoverVo;
import com.guli.guli_course.entity.EduChapter;
import com.guli.guli_course.entity.EduCourse;
import com.guli.guli_course.entity.EduCourseDescription;
import com.guli.guli_course.mapper.EduCourseMapper;
import com.guli.guli_course.service.EduChapterService;
import com.guli.guli_course.service.EduCourseDescriptionService;
import com.guli.guli_course.service.EduCourseService;
import com.guli.guli_course.service.feign.GuliBosFeign;
import com.guli.guli_course.vo.CourseInfoVo;
import com.guli.guli_course.vo.CoursePublishVo;
import com.guli.guli_course.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-04-29
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private GuliBosFeign bosFeign;

    @Override
    @Transactional
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {

        EduCourse eduCourse = courseInfoVo.getEduCourse();
        boolean eduCourseFlag = this.save(eduCourse);
        if (!eduCourseFlag) {
            throw new EduCourseException("20001", "课程基本信息添加失败");
        }

        String cid = eduCourse.getId();
        log.info("保存课程基本信息：{}", eduCourse);

        EduCourseDescription eduCourseDescription = courseInfoVo.getEduCourseDescription().setId(cid);

        boolean eduCourseDescriptionFlag = eduCourseDescriptionService.save(eduCourseDescription);
        if (!eduCourseDescriptionFlag) {
            throw new EduCourseException("20001", "课程描述添加失败");
        }

        return cid;
    }

    @Override
    @Transactional
    public String update(CourseInfoVo courseInfoVo) {
        boolean updateCourseById = this.updateById(courseInfoVo.getEduCourse());
        if (!updateCourseById) {
            throw new EduCourseException("20001", "课程基本信息修改失败");
        }

        boolean updateDescriptionById = eduCourseDescriptionService.saveOrUpdate(courseInfoVo.getEduCourseDescription());
        if (!updateDescriptionById) {
            throw new EduCourseException("20001", "课程描述修改失败");
        }
        return courseInfoVo.getEduCourse().getId();
    }

    @Override
    public CourseInfoVo getVoById(String id) {
        EduCourse eduCourse = getById(id);
        if (eduCourse == null) {
            throw new EduCourseException("20001", "查询客户基本信息失败");
        }

        EduCourseDescription descriptionById = eduCourseDescriptionService.getById(id);
        if (descriptionById == null) {
            return new CourseInfoVo().setEduCourse(eduCourse);
        }
        return new CourseInfoVo().setEduCourse(eduCourse).setEduCourseDescription(descriptionById);
    }

    @Override
    public Object pageCourse(Page<EduCourse> coursePage, QueryVo queryVo) {
        if (queryVo == null) {
            return this.page(coursePage, null);
        }

        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(queryVo.getSubjectId())) {
            wrapper.eq("subject_id", queryVo.getSubjectId());
        }

        if (!StringUtils.isEmpty(queryVo.getTeacherId())) {
            wrapper.eq("teacher_id", queryVo.getTeacherId());
        }

        if (!StringUtils.isEmpty(queryVo.getTitle())) {
            wrapper.like("title", queryVo.getTitle());
        }
        return this.page(coursePage, wrapper);
    }

    @Override
    public void deleteCourseByCid(String id) {
        //删除课程章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        List<EduChapter> list = eduChapterService.list(chapterQueryWrapper);
        if (list != null) {
            list.forEach(chapter -> eduChapterService.delChapterById(chapter.getId()));
        }
        //删除云端课程封面
        EduCourse course = getById(id);
        if (!StringUtils.isEmpty(course.getCover())) {
            BosCoverVo bosCoverVo = new BosCoverVo();
            bosCoverVo.setCover(course.getCover());
            bosFeign.remove(bosCoverVo);
        }
        boolean removeCourse = removeById(id);

        if (!removeCourse) {

            log.info("删除课程基本信息失败");
            throw new EduCourseException().setMessage("删除课程基本信息失败");
        }
        //删除课程详细信息
        if (eduCourseDescriptionService.getById(id) != null) {
            log.info("删除课程详细信息");

            boolean removeDescription = eduCourseDescriptionService.removeById(id);

            if (!removeDescription) {
                throw new EduCourseException().setMessage("删除课程详细信息失败");
            }
        }
    }

    @Override
    public CoursePublishVo getCoursePublishById(String id) {
        return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public int getCourseNum(String day) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<EduCourse>().eq("DATE(gmt_create)", day);

        Integer count = baseMapper.selectCount(wrapper);
        return count;
    }
}
