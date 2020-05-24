package com.guli.guli_course.service.impl;

import com.guli.guli_commom.exception.EduCourseException;
import com.guli.guli_commom.vo.Result;
import com.guli.guli_course.entity.EduVideo;
import com.guli.guli_course.mapper.EduVideoMapper;
import com.guli.guli_course.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.guli_course.service.feign.GuliVodFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-03
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private GuliVodFeign guliVodFeign;

    @Override
    public void delVideo(String id) {
        EduVideo eduVideo = baseMapper.selectById(id);
        //判断若存在视频，调用VOD删除
        String videoSourceId =  eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            Result result = guliVodFeign.delVideoById(videoSourceId);

            if (!result.isSuccess()){
                throw new EduCourseException().setMessage(result.getMessage());
            }
        }

        int i = baseMapper.deleteById(id);

        if(i<=0){
            throw new EduCourseException().setMessage("删除章节失败");
        }
    }
}
