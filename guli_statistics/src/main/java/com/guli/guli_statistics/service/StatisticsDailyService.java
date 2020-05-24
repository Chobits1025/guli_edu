package com.guli.guli_statistics.service;

import com.guli.guli_statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-05-21
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    /**
     * 根据日期生成统计数据
     * @param day
     */
    void createStatistics(String day);

    Map initChartData(String type, String begin, String end);
}
