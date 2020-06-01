package com.guli.guli_course.controller.frontpage;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.entity.EduCourse;
import com.guli.guli_course.service.EduCourseService;
import com.guli.guli_course.vo.CourseInfoVo;
import com.guli.guli_course.vo.QueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("edu/course")
@CrossOrigin
public class CourseController {

    @Autowired
    private EduCourseService eduCourseService;

    /**
     * 初始化首页课程信息
     * @return
     */
    @ApiOperation("初始化首页课程信息")
    @GetMapping("index")
    public Result initIndexCourse(){
        List<EduCourse> courses = eduCourseService.initIndexCourse();

        if(courses!=null&&courses.size()>0){
            return Result.ok().data("courses",courses);
        }
        return Result.error().message("没有查询到课程信息");
    }

    @ApiOperation("查询讲师对应课程")
    @GetMapping("getCourseByTeacherID/{teacherId}")
    public Result getCourseByTeacherID(@PathVariable("teacherId") String teacherId){
        List<EduCourse> courses = eduCourseService.getCourseByTeacherID(teacherId);

        if(courses!=null&&courses.size()>0){
            return Result.ok().data("courses",courses);
        }
        return Result.error().message("没有查询到课程信息");
    }

    @ApiOperation("分页获取课程信息")
    @PostMapping("/{page}/{size}")
    public Result list(@PathVariable int page, @PathVariable int size, @RequestBody QueryVo queryVo) {
        Page<EduCourse> coursePage = new Page<>(page, size);
        Object pageCourse = eduCourseService.pageCourse(coursePage, queryVo);

        if(pageCourse!=null){
            return Result.ok().data("courses", pageCourse);
        }

        return Result.error().message("没有查询到课程信息");
    }

    @ApiOperation("根据课程id查询课程详细信息")
    @GetMapping("{id}")
    public Result getCourseByID(@PathVariable("id") String id){
        CourseInfoVo courseInfoVo = eduCourseService.getVoById(id);

        if(courseInfoVo!=null){
            return Result.ok().data("courseInfoVo",courseInfoVo);
        }
        return Result.error().message("没有查询到课程信息");
    }


}
