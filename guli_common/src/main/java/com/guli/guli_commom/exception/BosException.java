package com.guli.guli_commom.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class BosException extends RuntimeException {
    private String code;
    private String msg;
}
