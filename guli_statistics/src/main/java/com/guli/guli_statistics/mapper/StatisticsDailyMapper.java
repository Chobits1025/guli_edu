package com.guli.guli_statistics.mapper;

import com.guli.guli_statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 网站统计日数据 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-05-21
 */
@Repository
public interface StatisticsDailyMapper extends BaseMapper<StatisticsDaily> {
    /**
     * 根据开始终止时间获取xDate
     * @param begin
     * @param end
     * @return
     */
    List getXDate(String begin,String end);

    /**
     * 查询课程数
     * @param x
     * @return
     */
    Integer getCourseNum(String x);

    /**
     * 注册数
     * @param x
     * @return
     */
    Integer getRegisterNum(String x);

    /**
     * 登录数
     * @param x
     * @return
     */
    Integer getLoginNum(String x);

    /**
     * 视频播放数
     * @param x
     * @return
     */
    Integer getVideoViewNum(String x);
}
