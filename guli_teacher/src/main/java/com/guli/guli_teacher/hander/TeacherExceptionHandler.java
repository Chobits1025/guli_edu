package com.guli.guli_teacher.hander;

import com.guli.guli_commom.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class TeacherExceptionHandler {
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return Result.error().message("出错了");
    }
}
