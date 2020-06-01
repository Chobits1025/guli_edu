package com.guli.guli_vod.controller;

import com.aliyuncs.exceptions.ClientException;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("vod")
@Api(tags = "视频点播")
@Slf4j
public class VodController {
    @Autowired
    private VodService vodService;


    @PostMapping("upload")
    @ApiOperation("上传视频")
    public Result uploadVideo(@RequestParam("file") MultipartFile file) {
        String videoId = vodService.uploadVideo(file);


        return StringUtils.isEmpty(videoId) ? Result.error() : Result.ok().data("videoId", videoId);
    }

    @ApiOperation("删除视频")
    @DeleteMapping("{id}")
    public Result delVideoById(@PathVariable String id){
        try {
            vodService.delVideoById(id);
            return Result.ok();

        } catch (ClientException e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }

    }

    @ApiOperation("根据视频ID获取视频凭证")
    @GetMapping("{vid}")
    public Result getPlayAutoByID(@PathVariable String vid){

        try {
            String playAuth = vodService.getPlayAutoByID(vid);
            log.info("获取到playAuth：{}",playAuth);

            return Result.ok().data("playAuth",playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            return Result.error().message("获取凭证失败");
        }

    }

}
