package com.guli.guli_ucenter.test;

import com.guli.guli_ucenter.service.UcenterMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UcenterTest {

    @Autowired
    UcenterMemberService ucenterMemberService;

    @Test
    public void ucenterTest(){
        ucenterMemberService.createStatistics("2019-01-19");
    }
}
