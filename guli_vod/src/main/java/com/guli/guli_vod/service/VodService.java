package com.guli.guli_vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    String uploadVideo(MultipartFile file);

    void delVideoById(String id) throws ClientException;
}
