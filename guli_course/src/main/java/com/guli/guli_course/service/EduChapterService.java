package com.guli.guli_course.service;

import com.guli.guli_course.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.guli_course.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-03
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 根据课程ID查询章节信息
     * @param cid
     * @return
     */
    List<ChapterVo> getChapterNestedList(String cid);

    void delChapterById(String id);
}
