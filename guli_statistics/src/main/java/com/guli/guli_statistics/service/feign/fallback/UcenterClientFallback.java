package com.guli.guli_statistics.service.feign.fallback;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_statistics.service.feign.UcenterClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UcenterClientFallback implements UcenterClient {
    @Override
    public Result createStatistics(String day) {
        log.info("Ucenter断路器");
        return Result.error().message("网络异常，请稍后再试").data("count",0);
    }
}
