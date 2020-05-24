package com.guli.guli_subject.controller;


import com.guli.guli_commom.vo.Result;
import com.guli.guli_subject.entity.EduSubject;
import com.guli.guli_subject.service.EduSubjectService;
import com.guli.guli_subject.vo.SubjectQueryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/subject")
@CrossOrigin
@Api(tags = "课程分类")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("/importSubject")
    @ApiOperation("Excel导入课程分类")
    public Result importSubject(MultipartFile file){
        List<String> errorMsg = eduSubjectService.importSubject(file);

        if(errorMsg==null||errorMsg.size()==0){
            return Result.ok();
        }
        return Result.error().data("message",errorMsg);
    }

    @GetMapping("listLvOne")
    @ApiOperation("获取所有一级分类")
    public Result listLvOne(){
        List<EduSubject> subjectsLvOne = eduSubjectService.listByParentId("0");
        return Result.ok().data("subjectsLvOne",subjectsLvOne);
    }

    @GetMapping("listLvTwo/{id}")
    @ApiOperation("获取所有二级分类")
    public Result listLvTwo(@PathVariable String id){
        List<EduSubject> subjectsLvTwo = eduSubjectService.listByParentId(id);
        return Result.ok().data("subjectsLvTwo",subjectsLvTwo);
    }

    @GetMapping("list")
    @ApiOperation("获取所有课程")
    public Result list(){
        List<SubjectQueryVO> list = eduSubjectService.list();
        if(list!=null&&list.size()>0){
            return Result.ok().data("subjectList",list);
        }
        return Result.error().message("没有查询到数据");
    }

    @GetMapping("getSubjectById/{id}")
    @ApiOperation("根据id查询分类")
    public Result getSubjectById(@PathVariable String id){
        EduSubject subject = eduSubjectService.getById(id);
        if(subject!=null){
            return Result.ok().data("subject",subject);
        }
        return Result.error().message("没有查询到数据");
    }


    @DeleteMapping("/removeById/{id}")
    @ApiOperation("根据ID删除分类信息")
    public Result removeById(@PathVariable String id){
        boolean remove = eduSubjectService.removeById(id);
        if(remove){
            return Result.ok();
        }
        return Result.error();
    }

    @PostMapping("saveLvOne")
    @ApiOperation("新增一级课程")
    public Result saveLvOne(@RequestBody EduSubject eduSubject){
        if(eduSubjectService.existsSubjectLvOne(eduSubject)){
            return Result.error().message("已存在的分类不能重复添加");
        }
        boolean result =  eduSubjectService.save(eduSubject);
        if(result) {
            return Result.ok();
        }else {
            return Result.error().message("服务器异常，请稍后再试");
        }
    }

    @PostMapping("saveLvTwo")
    @ApiOperation("添加二级分类")
    public Result saveLvTwo(@RequestBody EduSubject eduSubject){
        boolean save = eduSubjectService.save(eduSubject);
        if(save){
            return Result.ok();
        }
        return Result.error();
    }

    @PutMapping("update")
    @ApiOperation("修改分类信息分类")
    public Result update(@RequestBody EduSubject eduSubject){
        boolean update = eduSubjectService.updateById(eduSubject);
        if (update){
            return Result.ok();
        }
        return Result.error();
    }


}

