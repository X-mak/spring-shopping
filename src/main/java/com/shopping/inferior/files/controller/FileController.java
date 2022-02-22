package com.shopping.inferior.files.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.shopping.common.Result;
import com.shopping.inferior.files.service.FileService;
import com.shopping.utils.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/{type}")
    public Result<?> upload(MultipartFile file,@PathVariable String type){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        try{
            String name = fileService.uploadImg(file,type);
            name = "http://localhost:8080/files/"+type+"/"+name;
            return Result.success(name,"上传成功!");
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("上传失败!");
        }
    }

    @GetMapping("/{type}/{flag}")
    public void download(@PathVariable String type, @PathVariable String flag, HttpServletResponse response){
        //type决定下载的文件类型
        //userImg：用户头像上传
        //goodsImg：商品图片上传
        OutputStream os;
        String basePath = System.getProperty("user.dir") + "/src/main/resources/files/"+type+"/";
        List<String> fileNames = FileUtil.listFileNames(basePath);
        if(! fileNames.contains(flag))return ;
        try{
            if(StrUtil.isNotEmpty(flag)){
                response.addHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(flag,"UTF-8"));
                response.setContentType("application/octet-stream");
//                response.setContentType("video/webm");
                byte[] bytes = FileUtil.readBytes(basePath+flag);
                response.setHeader("Accept-Ranges", "bytes");
                response.setHeader("Content-Length", String.valueOf(bytes.length));
                os = response.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
            }
        }catch (Exception e){
            System.out.println("下载失败!");
        }
    }


    @Autowired
    FileService fileService;
    @Autowired
    Authority authority;
}
