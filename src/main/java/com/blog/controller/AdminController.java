package com.blog.controller;

import com.blog.constants.HttpStatusEnum;
import com.blog.model.User;
import com.blog.pojo.ResultVO;
import com.blog.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 15:28
 * @Version 1.0
 **/
@Api(tags = "用户登录接口类--Controller")
@RestController
@RequestMapping("/admin")
public class AdminController {
    /**
     * 功能描述: 登录接口
     * @param: [user]
     * @return: com.blog.pojo.ResultVO<java.util.Map<java.lang.String,java.lang.Object>>
     * @auther: lxl
     * @date: 2022/4/8 18:41
     */
    @ApiOperation("登录接口--login")
    @PostMapping("/user/login")
    public ResultVO<Map<String,Object>> login(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        if("admin".equals(user.getUsername())&&"password".equals(user.getPassword())){
            map.put("token",JwtUtils.generate(user.getUsername()));
            return new ResultVO<>(map);
        }
        return new ResultVO<>(HttpStatusEnum.FAILED,map);
    }
    /**
     * 功能描述：用token获取信息
     * @param: [token]
     * @return: com.blog.pojo.ResultVO<java.util.Map<java.lang.String,java.lang.Object>>
     * @auther: lxl
     * @date: 2022/4/8 18:41
     */
    @ApiOperation("获取用户信息--view")
    @GetMapping("/user/info")
    public ResultVO<Map<String,Object>> userInfo(String token){
        Map<String,Object> map = new HashMap<>();
        map.put("name",JwtUtils.parse(token).getSubject());
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return new ResultVO<>(map);
    }
    /**
     * 功能描述：退出登录
     * @param: []
     * @return: com.blog.pojo.ResultVO<java.util.Map<java.lang.String,java.lang.Object>>
     * @auther: lxl
     * @date: 2022/4/8 18:42
     */
    @ApiOperation("用户登出--logout")
    @PostMapping("/user/logout")
    public  ResultVO<Map<String,Object>> userLogout(){
        Map<String,Object> map = new HashMap<>();
        return new ResultVO<>(map);
    }
}
