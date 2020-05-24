package com.guli.guli_ucenter.controller;


import com.guli.guli_commom.vo.Result;
import com.guli.guli_ucenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-18
 */
@RestController
@RequestMapping("/ucenter")
@Api(tags = "用户中心")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;


    @ApiOperation("注册人数统计")
    @GetMapping("{day}")
    public Result createStatistics(@PathVariable("day") String day){

        Integer count = ucenterMemberService.createStatistics(day);

        return Result.ok().data("count",count);
    }
}

