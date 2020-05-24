package com.guli.guli_statistics.service.feign;

import com.guli.guli_commom.vo.Result;
import com.guli.guli_statistics.service.feign.fallback.UcenterClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "GULI-UCENTER",path = "/ucenter",fallback = UcenterClientFallback.class )
public interface UcenterClient {
    @GetMapping("/{day}")
    Result createStatistics(@PathVariable("day") String day);
}
