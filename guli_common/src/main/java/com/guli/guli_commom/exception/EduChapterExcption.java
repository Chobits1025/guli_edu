package com.guli.guli_commom.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EduChapterExcption extends RuntimeException{
    private String code;
    private String message;
}
