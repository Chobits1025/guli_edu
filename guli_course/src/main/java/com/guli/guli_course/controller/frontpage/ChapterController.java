package com.guli.guli_course.controller.frontpage;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.service.EduChapterService;
import com.guli.guli_course.vo.ChapterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("edu/chapter")
@CrossOrigin
public class ChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @GetMapping("getChaptersByCourseId/{courseId}")
    public Result getChaptersByCourseId(@PathVariable String courseId){

        List<ChapterVo> chapterVos = eduChapterService.getChapterNestedList(courseId);

        return Result.ok().data("chapterVos",chapterVos);
    }
}
