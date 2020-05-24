package com.avmask.guli_bos.controller;

import com.avmask.guli_bos.service.BosService;
import com.guli.guli_commom.vo.BosCoverVo;
import com.guli.guli_commom.vo.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Api("文件上传")
@RestController
@CrossOrigin
@RequestMapping("/bos")
public class BosController {

    @Autowired
    private BosService bosService;

    @PostMapping("/upload")
    public Result upload(@RequestParam("file")MultipartFile file,@RequestParam(value = "host",required = false)String host){

        String path;
        try {
            path = bosService.upload(file,host);
            return Result.ok().data("path",path);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Result remove(@RequestBody BosCoverVo cover){

        boolean remove = bosService.remove(cover.getCover());

        if (remove){
            return Result.ok();
        }

        return Result.error();
    }
}
