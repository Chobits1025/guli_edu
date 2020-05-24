package com.guli.guli_vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

public class AliyunVODSDKUtils {
    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client,String VideoId) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId(VideoId);
        return client.getAcsResponse(request);
    }

    /*获取播放凭证函数*/
    public static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client,String VideoId) throws Exception {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(VideoId);
        return client.getAcsResponse(request);
    }

}
