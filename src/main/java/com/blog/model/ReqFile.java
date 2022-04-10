package com.blog.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ReqFile
 * @Description TODO
 * @Author admin
 * @Date 2022/4/8 21:40
 * @Version 1.0
 **/
@Data
public class ReqFile {
    private MultipartFile image;
}
