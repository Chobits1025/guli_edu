package com.guli.guli_statistics.controller;


import com.guli.guli_commom.vo.Result;
import com.guli.guli_statistics.service.StatisticsDailyService;
import com.guli.guli_statistics.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2020-05-21
 */
@RestController
@RequestMapping("/daily")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;

    @GetMapping("{day}")
    public Result createStatistics(@PathVariable("day") String day) {

        try {
            statisticsDailyService.createStatistics(day);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }

        return Result.ok();
    }

    @GetMapping("{type}/{begin}/{end}")
    public Result initChartData(@PathVariable String type, @PathVariable String begin, @PathVariable String end) {
        try {
            Map map = statisticsDailyService.initChartData(type, begin, end);
            return Result.ok().data("xDate",map.get("xDate")).data("yDate",map.get("yDate"));
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }
    }
}

