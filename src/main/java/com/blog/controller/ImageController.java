package com.blog.controller;

import com.blog.constants.UploadConstants;
import com.blog.pojo.ResultVO;
import com.blog.utils.UploadFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ImageController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/8 20:13
 * @Version 1.0
 **/
@RestController
@Api(tags = "图片接口类--Controller")
@RequestMapping("/image")
public class ImageController {
    /**
     * 功能描述：文章图片上传功能
     * @param: [image]
     * @return: com.blog.pojo.ResultVO<java.util.Map<java.lang.String,java.lang.Object>>
     * @auther: lxl
     * @date: 2022/4/9 13:47
     */
    @ApiOperation("文章图片上传--upload")
    @PostMapping("/blog/upload")
    public ResultVO<Map<String,Object>> uploadImage(@RequestParam("image") MultipartFile image){
        String suffixName = UploadFileUtils.getSuffixName(image);
        String newFileName = UploadFileUtils.getNewFileName(suffixName);
        File fileDirectory = new File(UploadConstants.FILE_UPLOAD_DIC);
        File destFile = new File(UploadConstants.FILE_UPLOAD_DIC+newFileName);
        Map<String,Object> map = new HashMap<>();
        try {
            if (!fileDirectory.exists() && !fileDirectory.mkdirs()) {
                throw new IOException("文件夹创建失败,路径为:" + fileDirectory);
            }
            image.transferTo(destFile);
            String fileUrl = "http://localhost:8081"+UploadConstants.FILE_SQL_DIC + newFileName;
            map.put("url",fileUrl);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ResultVO<>(map);
    }
}
