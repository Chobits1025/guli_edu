package com.guli.guli_course.controller;


import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.entity.EduVideo;
import com.guli.guli_course.service.EduVideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/video")
@CrossOrigin
@Api(tags = "课时")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @GetMapping("{id}")
    public Result getVideoById(@PathVariable String id){
        EduVideo video = eduVideoService.getById(id);
        if(video==null){
            return Result.error();
        }

        return Result.ok().data("video",video);
    }

    @PostMapping("saveOrUpdateVideo")
    public Result saveOrUpdateVideo(@RequestBody EduVideo eduVideo){

        return eduVideoService.saveOrUpdate(eduVideo)?Result.ok():Result.error();
    }

    @DeleteMapping("{id}")
    public Result delVideo(@PathVariable String id){
        try {
            eduVideoService.delVideo(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }
        return Result.ok();
    }
}

