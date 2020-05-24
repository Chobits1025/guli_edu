package com.guli.guli_course.service.feign;

import com.guli.guli_commom.vo.BosCoverVo;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.service.feign.fallback.GuliBosFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "GULI-BOS", path = "/bos", fallback = GuliBosFeignFallback.class)
public interface GuliBosFeign {

    @PostMapping("/upload")
    Result upload(@RequestParam("file") MultipartFile file, @RequestParam(value = "host", required = false) String host);

    @DeleteMapping("/delete")
    Result remove(BosCoverVo cover);
}
