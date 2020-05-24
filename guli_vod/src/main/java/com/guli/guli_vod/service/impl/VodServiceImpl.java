package com.guli.guli_vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.guli.guli_vod.exception.VodException;
import com.guli.guli_vod.service.VodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class VodServiceImpl implements VodService {
    @Autowired
    private DefaultAcsClient defaultAcsClient;

    @Value("${vod.access-key-id}")
    private String accessKeyId;

    @Value("${vod.access-key-secret}")
    private String accessKeySecret;


    @Override
    public String uploadVideo(MultipartFile file) {

        String filename = file.getOriginalFilename();

        System.out.println(filename+"========"+file.getName());

        try {
            InputStream is = file.getInputStream();
            assert filename != null;
            String title = filename.substring(0,filename.indexOf("."));
            UploadStreamRequest request = new UploadStreamRequest(accessKeyId, accessKeySecret, title, filename, is);

            UploadVideoImpl uploadVideo = new UploadVideoImpl();

            UploadStreamResponse response = uploadVideo.uploadStream(request);

            if (!response.isSuccess()){
                throw new VodException().setMessage("视频上传失败").setCode(response.getCode());
            }


            return response.getVideoId();



        } catch (IOException e) {
            e.printStackTrace();
            log.error("{}上传失败",filename);
            throw new VodException().setMessage("阿里云上传错误").setCode("200001");
        }
    }

    @Override
    public void delVideoById(String id) throws ClientException {
        DeleteVideoRequest request = new DeleteVideoRequest();
        request.setVideoIds(id);

        defaultAcsClient.getAcsResponse(request);



    }
}
