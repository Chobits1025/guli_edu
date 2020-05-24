package com.guli.guli_commom.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BosExceptionEnum {
    FILEUPLOADFAIL("0","文件上传失败"),
    FILENOTFOUNT("1","文件不存在"),
    FILETYPEEXCEPTION("2","文件格式不正确"),
    FILEISNOTIMAGE("3","请传入正确的图片");

    private String code;
    private String msg;
    }
