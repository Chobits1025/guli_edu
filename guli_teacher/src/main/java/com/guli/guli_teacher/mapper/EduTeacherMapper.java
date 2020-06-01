package com.guli.guli_teacher.mapper;

import com.guli.guli_teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
@Repository
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    List<EduTeacher> initIndexTeacher();
}
