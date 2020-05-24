package com.guli.guli_course.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.guli_commom.exception.EduChapterExcption;
import com.guli.guli_commom.exception.EduCourseException;
import com.guli.guli_course.entity.EduChapter;
import com.guli.guli_course.entity.EduVideo;
import com.guli.guli_course.mapper.EduChapterMapper;
import com.guli.guli_course.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.guli_course.service.EduVideoService;
import com.guli.guli_course.vo.ChapterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-03
 */
@Service
@Slf4j
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterNestedList(String cid) {
        ArrayList<ChapterVo> chapterVos = new ArrayList<>();

        List<EduChapter> chapters = this.list(new QueryWrapper<EduChapter>().eq("course_id", cid).orderByAsc("sort"));
        log.info("查询所有课程大纲{}", chapters);

        for (EduChapter chapter : chapters) {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);

            QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
            wrapper.eq("chapter_id", chapter.getId());
            wrapper.orderByAsc("sort");

            List<EduVideo> list = eduVideoService.list(wrapper);
            log.info("根据大纲查询对应课程视频:{}", list);

            if (list != null && list.size() > 0) {
                chapterVo.setChildren(list);
            }

            chapterVos.add(chapterVo);

        }

        return chapterVos;
    }

    @Override
    @Transactional
    public void delChapterById(String id) {

        if (getById(id) == null) {
            throw new EduCourseException().setMessage("没有查询到对应的课程大纲信息");
        }
        //查询该课程对应章节
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<EduVideo>().eq("chapter_id", id);
        List<EduVideo> videos = eduVideoService.list(wrapper);
        //遍历删除章节及云端视频
        try {
            videos.forEach(eduVideo -> {
                eduVideoService.delVideo(eduVideo.getId());
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        boolean removeById = this.removeById(id);

        if(!removeById){
            throw new EduChapterExcption().setMessage("删除失败");
        }
    }
}
