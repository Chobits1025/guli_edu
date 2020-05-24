package com.guli.guli_statistics.service.feign;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_statistics.service.feign.fallback.CourseClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "GULI-COURSE",path = "/course",fallback = CourseClientFallback.class )
public interface CourseClient {
    @GetMapping("/course_num/{day}")
    Result getCourseNum(@PathVariable("day") String day);
}
