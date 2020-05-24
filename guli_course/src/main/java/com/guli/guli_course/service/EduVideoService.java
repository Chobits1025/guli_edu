package com.guli.guli_course.service;

import com.guli.guli_course.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-03
 */
public interface EduVideoService extends IService<EduVideo> {

    void delVideo(String id);
}
