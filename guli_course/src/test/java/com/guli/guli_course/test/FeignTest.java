package com.guli.guli_course.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guli.guli_commom.vo.BosCoverVo;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.service.feign.GuliBosFeign;
import com.guli.guli_course.service.feign.GuliVodFeign;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.PathVariable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeignTest {
    @Autowired
    GuliVodFeign guliVodFeign;

    @Autowired
    GuliBosFeign bosFeign;

    @Test
    public void TestFeign() throws JsonProcessingException {
        Result result = guliVodFeign.delVideoById("aa");

        String s = new ObjectMapper().writeValueAsString(result);

        System.out.println(s);
    }

    @Test
    public void  bosRmImg(){
        BosCoverVo bosCoverVo = new BosCoverVo();
        bosCoverVo.setCover("https://chobits.bj.bcebos.com/0574eaaaddbf4f598acf6fefffc0566b_file.png");
        bosFeign.remove(bosCoverVo);
    }
}
