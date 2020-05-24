package com.avmask.guli_bos.service;

import org.springframework.web.multipart.MultipartFile;

public interface BosService {
    String upload(MultipartFile file, String host);

    boolean remove(String cover);
}
