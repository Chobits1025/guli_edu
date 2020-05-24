package com.guli.guli_course.vo;

import com.guli.guli_course.entity.EduCourse;
import com.guli.guli_course.entity.EduCourseDescription;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@ApiModel(value="CourseInfoVo", description="课程基本信息")
@Data
@Accessors(chain = true)
public class CourseInfoVo {

    @ApiModelProperty("课程信息")
    private EduCourse eduCourse;

    @ApiModelProperty("课程描述")
    private EduCourseDescription eduCourseDescription;
}
