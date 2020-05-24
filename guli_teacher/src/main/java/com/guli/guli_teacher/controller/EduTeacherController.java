package com.guli.guli_teacher.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_teacher.entity.EduTeacher;
import com.guli.guli_teacher.query.TeacherQuery;
import com.guli.guli_teacher.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
@Api(tags = "讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/teacher")
@Slf4j
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "所有讲师列表", notes = "无条件查询所有讲师信息")
    @GetMapping()
    public Result listTeacher() {
        List<EduTeacher> teachers = eduTeacherService.list(null);
        if (teachers != null && teachers.size() > 0) {
            return Result.ok().data("teachers", teachers);
        } else {
            return Result.error();
        }

    }

    @DeleteMapping("/{id}")
    @ApiOperation(notes = "根据id删除讲师信息", value = "删除讲师")
    public Result removeTeacherById(@PathVariable("id") @ApiParam(required = true, value = "讲师ID", name = "id") String id) {

        boolean b = eduTeacherService.removeById(id);
        if (!b) return Result.error();

        return Result.ok();
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

    @GetMapping("list")
    @ApiOperation("查询所有讲师信息")
    public Result list(){
        List<EduTeacher> eduTeachers = eduTeacherService.list(null);

        return Result.ok().data("eduTeachers",eduTeachers);
    }

    @ApiOperation("新增讲师")
    @PostMapping("/add")
    public Result saveTeacher(@ApiParam(name="teacher",value = "讲师信息")@RequestBody EduTeacher teacher){
        boolean save = eduTeacherService.save(teacher);
        if(save){
            return Result.ok();
        }

        return Result.error();
    }

    @ApiOperation("根据ID查询讲师信息")
    @GetMapping("/{id}")
    public Result getTeacherById(@ApiParam(name = "id",value = "讲师ID",required = true)@PathVariable String id){

        EduTeacher teacher = eduTeacherService.getById(id);
        if(teacher!=null){
            return Result.ok().data("teacher",teacher);
        }
        return Result.error();
    }

    @ApiOperation("修改讲师信息")
    @PutMapping("/edit")
    public Result updateTeacher(@ApiParam(name="teacher",value = "讲师信息")@RequestBody EduTeacher teacher){
        boolean b = eduTeacherService.updateById(teacher);
        if(b){
            return Result.ok();
        }
        return Result.error();
    }

}

