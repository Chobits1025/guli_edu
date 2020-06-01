package com.guli.guli_course.mapper;

import com.guli.guli_course.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.guli_course.vo.CoursePublishVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-29
 */
@Repository
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 根据ID查询课程发布页面信息
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     * 初始化前台首页课程信息
     * @return
     */
    List<EduCourse> initIndexCourse();
}
