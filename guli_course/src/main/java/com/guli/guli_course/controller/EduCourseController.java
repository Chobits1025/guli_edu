package com.guli.guli_course.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.entity.EduCourse;
import com.guli.guli_course.service.EduCourseService;
import com.guli.guli_course.vo.CourseInfoVo;
import com.guli.guli_course.vo.CoursePublishVo;
import com.guli.guli_course.vo.QueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-29
 */
@Api(tags = "课程信息", value = "管理课程信息")
@RestController
@RequestMapping("/course")
@Slf4j
@CrossOrigin
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation("根据ID获取课程信息")
    @GetMapping("{id}")
    public Result getCourseById(@PathVariable String id) {

        try {
            CourseInfoVo courseInfoVo = eduCourseService.getVoById(id);
            return Result.ok().data("courseInfoVo", courseInfoVo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }


    }

    @ApiOperation("保存课程信息")
    @PostMapping("save")
    public Result save(@RequestBody CourseInfoVo courseInfoVo) {
        try {
            String cid = eduCourseService.saveCourseInfo(courseInfoVo);
            return Result.ok().data("courseId", cid);
        } catch (Exception e) {
            log.info("出错了：{}", e.getMessage());
            e.printStackTrace();
        }
        return Result.error();
    }

    @ApiOperation("修改课程信息")
    @PutMapping("update")
    public Result update(@RequestBody CourseInfoVo courseInfoVo) {
        try {
            String cid = eduCourseService.update(courseInfoVo);
            return Result.ok().data("courseId", cid);
        } catch (Exception e) {
            log.info("出错了：{}", e.getMessage());
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }
    }

    @ApiOperation("课程发布")
    @PutMapping("{id}")
    public Result publish(@PathVariable String id) {
        boolean publish = eduCourseService.updateById(new EduCourse().setId(id).setStatus("Normal"));
        return publish ? Result.ok() : Result.error();
    }

    @ApiOperation("分页获取课程信息")
    @PostMapping("/{page}/{size}")
    public Result list(@PathVariable int page, @PathVariable int size, @RequestBody QueryVo queryVo) {
        Page<EduCourse> coursePage = new Page<>(page, size);

        return Result.ok().data("rows", eduCourseService.pageCourse(coursePage, queryVo));
    }

    @ApiOperation("删除课程信息")
    @DeleteMapping("{id}")
    public Result deleteCourseByCid(@PathVariable String id) {

        try {
            eduCourseService.deleteCourseByCid(id);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }

    }

    @ApiOperation("根据课程ID查询课程发布信息")
    @GetMapping("vo/{id}")
    public Result getCoursePublishById(@PathVariable String id) {

        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishById(id);
        if (coursePublishVo == null) {
            return Result.error().message("没有查询到任何信息");
        }
        return Result.ok().data("coursePublish", coursePublishVo);
    }

    @GetMapping("course_num/{day}")
    public Result getCourseNum(@PathVariable("day") String day){
        int count = eduCourseService.getCourseNum(day);
        return Result.ok().data("course_num",count);
    }
}

