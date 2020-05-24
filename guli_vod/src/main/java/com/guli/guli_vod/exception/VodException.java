package com.guli.guli_vod.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class VodException extends RuntimeException{
    private String code;
    private String message;
}
