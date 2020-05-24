package com.guli.guli_course.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("课程发布VO")
@Data
public class CoursePublishVo {
    @ApiModelProperty("课程ID")
    private String id;

    @ApiModelProperty("课程封面")
    private String cover;

    @ApiModelProperty("课时")
    private Integer lessonNum;

    @ApiModelProperty("一级分类")
    private String subjectParentTitle;

    @ApiModelProperty("二级分类")
    private String subjectTitle;

    @ApiModelProperty("讲师")
    private String teacherName;

    @ApiModelProperty("金额")
    private double price;

}
