package com.guli.guli_course.vo;

import com.guli.guli_course.entity.EduChapter;
import com.guli.guli_course.entity.EduVideo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ChapterVo extends EduChapter {
    private List<EduVideo> children = new ArrayList<>();
}
