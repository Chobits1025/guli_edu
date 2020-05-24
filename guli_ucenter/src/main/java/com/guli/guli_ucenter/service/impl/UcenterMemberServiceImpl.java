package com.guli.guli_ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.guli_ucenter.entity.UcenterMember;
import com.guli.guli_ucenter.mapper.UcenterMemberMapper;
import com.guli.guli_ucenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-18
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public Integer createStatistics(String day) {
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("DATE(gmt_create)",day);

        return baseMapper.selectCount(wrapper);
    }
}
