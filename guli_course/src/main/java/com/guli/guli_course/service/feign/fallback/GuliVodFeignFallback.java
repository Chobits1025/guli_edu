package com.guli.guli_course.service.feign.fallback;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.service.feign.GuliVodFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class GuliVodFeignFallback implements GuliVodFeign {
    @Override
    public Result uploadVideo(MultipartFile file) {
        return Result.error().message("fallback:网络异常，请稍后再试");
    }

    @Override
    public Result delVideoById(String id) {
        return Result.error().message("fallback:网络异常，请稍后再试");

    }
}
