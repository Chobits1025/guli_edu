package com.guli.guli_statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.guli_commom.exception.StatisticsDailyException;
import com.guli.guli_statistics.entity.StatisticsDaily;
import com.guli.guli_statistics.mapper.StatisticsDailyMapper;
import com.guli.guli_statistics.service.StatisticsDailyService;
import com.guli.guli_statistics.service.feign.CourseClient;
import com.guli.guli_statistics.service.feign.UcenterClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2020-05-21
 */
@Service
@Slf4j
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private StatisticsDailyMapper statisticsDailyMapper;

    @Override
    public void createStatistics(String day) {
        //查询当前日期是否存在数据，若存在删除
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("date_calculated", day);

        List<StatisticsDaily> statisticsDailies = baseMapper.selectList(wrapper);

        if (statisticsDailies != null && statisticsDailies.size() > 0) {
            log.info("{}已存在统计信息，删除", day);
            statisticsDailies.forEach(statisticsDaily -> {
                int delete = baseMapper.deleteById(statisticsDaily.getId());

                if (delete == 0) {
                    throw new StatisticsDailyException().setMessage("清除统计信息失败");
                }

            });
        }

        //调用Ucenter服务查询用户注册数量
        int registerNum = (int) ucenterClient.createStatistics(day).getData().get("count");
        log.info("{}注册数为{}", day, registerNum);
        //登录数，播放数暂取随机数
        int loginNum = RandomUtils.nextInt(100, 200);
        int videoViewNum = RandomUtils.nextInt(100, 200);

        //调用course服务获取新增课程数
        int courseNum = (int) courseClient.getCourseNum(day).getData().get("course_num");
        log.info("{}新增课程数{}", day, courseNum);

        StatisticsDaily daily = new StatisticsDaily();
        daily.setCourseNum(courseNum);
        daily.setLoginNum(loginNum);
        daily.setRegisterNum(registerNum);
        daily.setVideoViewNum(videoViewNum);

        daily.setDateCalculated(day);

        int insert = baseMapper.insert(daily);

        if (insert == 0) {
            throw new StatisticsDailyException().setMessage("插入统计信息失败");
        }
    }

    @Override
    public Map initChartData(String type, String begin, String end) {
        //xDate
        List<String> xDate = statisticsDailyMapper.getXDate(begin, end);
        HashMap<String, List> map = new HashMap<>();
        if (xDate != null && xDate.size() > 0) {
            map.put("xDate", xDate);
        } else {
            throw new StatisticsDailyException().setMessage("该区间没有对应数据");
        }

        //yDate
        ArrayList<Integer> yDate = new ArrayList<>();
        xDate.forEach(x -> {
            switch (type) {
                case "course_num":
                    yDate.add(statisticsDailyMapper.getCourseNum(x));
                    break;
                case "login_num":
                    yDate.add(statisticsDailyMapper.getLoginNum(x));
                    break;
                case "register_num":
                    yDate.add(statisticsDailyMapper.getRegisterNum(x));
                    break;
                case "video_view_num":
                    yDate.add(statisticsDailyMapper.getVideoViewNum(x));
                    break;
                default:
                    throw new StatisticsDailyException().setMessage("请选择正确的统计类型");
            }
        });
        map.put("yDate",yDate);
        return map;
    }
}
