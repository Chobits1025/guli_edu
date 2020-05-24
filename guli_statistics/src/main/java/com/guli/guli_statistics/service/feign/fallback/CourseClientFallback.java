package com.guli.guli_statistics.service.feign.fallback;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_statistics.service.feign.CourseClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CourseClientFallback implements CourseClient {

    @Override
    public Result getCourseNum(String day) {
        log.info("Course断路器");
        return Result.error().message("服务器异常，请稍后").data("course_num",0);
    }
}
