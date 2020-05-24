package com.guli.guli_ucenter.service;

import com.guli.guli_ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-18
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    Integer createStatistics(String day);
}
