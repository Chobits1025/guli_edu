package com.guli.guli_subject.service;

import com.guli.guli_subject.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.guli_subject.vo.SubjectQueryVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-04-27
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importSubject(MultipartFile file);

    List<SubjectQueryVO> list();

    boolean existsSubjectLvOne(EduSubject eduSubject);

    List<EduSubject> listByParentId(String s);
}
