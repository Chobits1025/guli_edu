package com.guli.guli_vod;

import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.guli.guli_vod.utils.AliyunVODSDKUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class test {
    @Autowired
    DefaultAcsClient defaultAcsClient;

    @Test
    public void getPlayURL(){
        try {
            GetPlayInfoResponse response = AliyunVODSDKUtils.getPlayInfo(defaultAcsClient, "48e63ddf87bb4ac998bf38af6d29fc2b");
            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();

            playInfoList.forEach(playInfo -> System.out.println(playInfo.getPlayURL()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
