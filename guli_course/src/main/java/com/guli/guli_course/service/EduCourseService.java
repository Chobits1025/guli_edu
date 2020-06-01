package com.guli.guli_course.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_course.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.guli_course.vo.CourseInfoVo;
import com.guli.guli_course.vo.CoursePublishVo;
import com.guli.guli_course.vo.QueryVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-29
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    String update(CourseInfoVo courseInfoVo);

    CourseInfoVo getVoById(String id);

    Object pageCourse(Page<EduCourse> coursePage, QueryVo queryVo);

    void deleteCourseByCid(String id);

    CoursePublishVo getCoursePublishById(String id);

    int getCourseNum(String day);

    List<EduCourse> initIndexCourse();

    List<EduCourse> getCourseByTeacherID(String teacherId);
}
