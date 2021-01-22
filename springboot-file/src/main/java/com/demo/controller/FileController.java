package com.demo.controller;

import com.demo.config.BearConfig;
import com.demo.config.Constants;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
       //重命名文件
        String filename = file.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf('.'));
        String newFileName = UUID.randomUUID()+ext;
        //存储文件
        String path = BearConfig.getPath();
        File newFile = new File(path, newFileName);
        if (!newFile.getParentFile().exists()){
            newFile.getParentFile().mkdirs();
        }
        file.transferTo(newFile);
        //返回虚拟地址
        StringBuffer requestURL = request.getRequestURL();
        String requestURI = request.getRequestURI();
        String substring = requestURL.substring(0, requestURL.length() - requestURI.length());
        return substring + Constants.RESOURCE_PREFIX + newFileName;
    }
}
