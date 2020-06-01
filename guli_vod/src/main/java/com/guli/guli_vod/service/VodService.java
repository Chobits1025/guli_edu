package com.guli.guli_vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

public interface VodService {
    /**
     * 上传视频
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据视频ID删除云端视频
     * @param id
     * @throws ClientException
     */
    void delVideoById(String id) throws ClientException;

    /**
     * 根据视频ID获取播放凭证
     * @param vid
     * @return
     */
    String getPlayAutoByID(String vid) throws ClientException;
}
