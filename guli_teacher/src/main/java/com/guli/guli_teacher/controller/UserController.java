package com.guli.guli_teacher.controller;

import com.guli.guli_commom.vo.Result;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @PostMapping("login")
    @ResponseBody
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("info")
    @ResponseBody
    public Result info(){
        //"data":{"roles":["admin"],"name":"admin",
        // "avatar":"https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
        return Result.ok().data("roles","[\"admin\"]")
                .data("name","admin")
                .data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }


}
