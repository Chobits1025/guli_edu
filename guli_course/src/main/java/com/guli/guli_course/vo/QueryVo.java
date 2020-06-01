package com.guli.guli_course.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="QueryVo", description="课程查询条件")
@Data
public class QueryVo {
    @ApiModelProperty("课程分类ID")
    private String subjectId;

    @ApiModelProperty("讲师ID")
    private String teacherId;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("排序字段")
    private String orderBy;
}
