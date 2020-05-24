package com.guli.guli_course.controller;


import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.entity.EduChapter;
import com.guli.guli_course.entity.EduVideo;
import com.guli.guli_course.service.EduChapterService;
import com.guli.guli_course.vo.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/chapter")
@Api(tags = "章节信息")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("list/{cid}")
    @ApiOperation("获取所有章节信息")
    public Result getChapterNestedList(@PathVariable String cid) {
        List<ChapterVo> chapterNestedList = eduChapterService.getChapterNestedList(cid);

        return Result.ok().data("chapterNestedList", chapterNestedList);

    }

    @GetMapping("{id}")
    @ApiOperation("根据ID查询大纲信息")
    public Result getChapterById(@PathVariable String id) {

        EduChapter chapter = eduChapterService.getById(id);

        if (chapter != null) {
            return Result.ok().data("chapter", chapter);

        }
        return Result.error();
    }

    @PostMapping("saveOrUpdate")
    @ApiOperation("保存或修改课程大纲")
    public Result saveOrUpdate(@RequestBody EduChapter chapter) {
        boolean flag = eduChapterService.saveOrUpdate(chapter);

        if (flag) {
            return Result.ok();
        }

        return Result.error();
    }

    @DeleteMapping("{id}")
    @ApiOperation("删除课程大纲")
    public Result delChapterById(@PathVariable String id) {
        try {
            eduChapterService.delChapterById(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }
    }
}

