package com.guli.guli_commom.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "讲师异常处理")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduTeacherException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private int code;
    @ApiModelProperty("错误信息")
    private String massage;
}
