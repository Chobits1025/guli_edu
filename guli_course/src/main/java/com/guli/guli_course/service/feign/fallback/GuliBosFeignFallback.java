package com.guli.guli_course.service.feign.fallback;

import com.guli.guli_commom.vo.BosCoverVo;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.service.feign.GuliBosFeign;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class GuliBosFeignFallback implements GuliBosFeign {
    @Override
    public Result upload(MultipartFile file, String host) {
        return Result.error().message("fallback:网络异常");
    }

    @Override
    public Result remove(BosCoverVo cover) {
        return Result.error().message("fallback:网络异常");
    }
}
