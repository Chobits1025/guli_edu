package com.avmask.guli_bos.service.impl;

import com.avmask.guli_bos.service.BosService;
import com.avmask.guli_bos.utils.FileUpLoadUtils;
import com.guli.guli_commom.enums.BosExceptionEnum;
import com.guli.guli_commom.exception.BosException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
public class BosServiceImpl implements BosService {
    private static String TYPESTR[] = {".png", ".jpg", ".bmp", ".gif", ".jpeg"};

    @Autowired
    private FileUpLoadUtils fileUpLoadUtils;

    @Override
    public String upload(MultipartFile file, String host) {
        if (file.isEmpty()) {
            log.info("空文件");
            throw new BosException(BosExceptionEnum.FILENOTFOUNT.getCode(), BosExceptionEnum.FILENOTFOUNT.getMsg());
        }

        String fileName = file.getOriginalFilename();

        boolean flag = false;
        for (String s : TYPESTR) {
            if (StringUtils.endsWithIgnoreCase(fileName, s)) {
                flag = true;
            }
        }
        if (!flag) {
            log.info("只能上传{}格式", Arrays.toString(TYPESTR));
            throw new BosException(BosExceptionEnum.FILETYPEEXCEPTION.getCode(), BosExceptionEnum.FILETYPEEXCEPTION.getMsg());
        }

        try {
            BufferedImage read = ImageIO.read(file.getInputStream());

            if (read == null) {
                log.info("不是图片文件");
                throw new BosException(BosExceptionEnum.FILEISNOTIMAGE.getCode(), BosExceptionEnum.FILEISNOTIMAGE.getMsg());
            }
            return fileUpLoadUtils.ImgUpLoad(file.getInputStream(), fileName,host);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BosException(BosExceptionEnum.FILEUPLOADFAIL.getCode(), BosExceptionEnum.FILEUPLOADFAIL.getMsg());
        }
    }

    @Override
    public boolean remove(String cover) {
        try {
            fileUpLoadUtils.rmImg(cover);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
