package com.blog.controller;

import com.blog.model.User;
import com.blog.pojo.ResultVO;
import com.blog.utils.JwtUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 15:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody User user){
        if("admin".equals(user.getUsername())&&"password".equals(user.getPassword())){
            return new ResultVO<>(JwtUtils.generate(user.getUsername()));
        }
        return new ResultVO<>("账号或密码错误");
    }
}
