package com.guli.guli_teacher.controller.frontpage;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_teacher.entity.EduTeacher;
import com.guli.guli_teacher.query.TeacherQuery;
import com.guli.guli_teacher.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("edu/teacher")
@CrossOrigin
public class TeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("index")
    public Result initIndexTeacher(){
        List<EduTeacher> eduTeachers = eduTeacherService.initIndexTeacher();
        if(eduTeachers!=null&&eduTeachers.size()>0){
            return Result.ok().data("teachers",eduTeachers);
        }

        return Result.error().message("没有查询到讲师信息");
    }

    @ApiOperation(value = "分页查询讲师信息")
    @PostMapping("/{page}/{size}")
    public Result listTeacherByPage(@ApiParam(name = "page", value = "当前页", example = "1") @PathVariable int page,
                                    @ApiParam(name = "size", value = "每页条数", example = "5") @PathVariable int size,
                                    @RequestBody@ApiParam(name = "teacherQuery") TeacherQuery teacherQuery) {

        Page<EduTeacher> teacherPage = new Page<>(page, size);
        IPage<EduTeacher> iPage = eduTeacherService.pageQuery(teacherPage, teacherQuery);
        if (iPage != null) {
            return Result.ok().data("pageInfo", iPage);
        }
        return Result.error().message("没有查询到任何数据");
    }

    @ApiOperation("根据id查询讲师信息")
    @GetMapping("{id}")
    public Result getTeacherById(@PathVariable("id") String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);

        if (eduTeacher!=null){
            return Result.ok().data("teacher",eduTeacher);
        }

        return Result.error().message("没有查询到对应的信息");
    }
}
