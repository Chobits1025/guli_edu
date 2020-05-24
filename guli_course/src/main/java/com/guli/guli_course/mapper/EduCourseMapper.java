package com.guli.guli_course.mapper;

import com.guli.guli_course.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guli.guli_course.vo.CoursePublishVo;
import org.springframework.stereotype.Repository;

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
    CoursePublishVo getCoursePublishVoById(String id);
}
