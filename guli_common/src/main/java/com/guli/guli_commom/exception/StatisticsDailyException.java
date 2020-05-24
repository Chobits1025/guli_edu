package com.guli.guli_commom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDailyException extends RuntimeException{
    private String code;
    private String message;
}
