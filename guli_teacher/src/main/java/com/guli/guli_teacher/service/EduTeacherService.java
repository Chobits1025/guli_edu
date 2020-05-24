package com.guli.guli_teacher.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.guli_teacher.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.guli_teacher.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-20
 */
public interface EduTeacherService extends IService<EduTeacher> {

    IPage<EduTeacher> pageQuery(Page<EduTeacher> teacherPage, TeacherQuery teacherQuery);
}
