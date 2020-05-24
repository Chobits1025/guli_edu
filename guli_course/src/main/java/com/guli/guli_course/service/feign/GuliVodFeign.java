package com.guli.guli_course.service.feign;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.service.feign.fallback.GuliVodFeignFallback;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "GULI-VOD",path = "vod",fallback = GuliVodFeignFallback.class)
public interface GuliVodFeign {

    @PostMapping("upload")
    @ApiOperation("上传视频")
    Result uploadVideo(@RequestParam("file") MultipartFile file) ;

    @ApiOperation("删除视频")
    @DeleteMapping("{id}")
    Result delVideoById(@PathVariable String id);
}
