package com.shopping.inferior.files.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    String uploadImg(MultipartFile file,String type)throws IOException;
}
