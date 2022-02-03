package com.shopping.inferior.authentication.controller;

import com.shopping.common.Result;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.RegisterUser;
import com.shopping.entity.authentication.UserAccount;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.inferior.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @PostMapping
    public Result<?> addUser(@RequestBody RegisterUser registerUser){
        int res = authenticationService.addUser(registerUser);
        if(res == 1)return Result.success("登陆成功!");
        else if(res == 0)return Result.error("存在重复账号!");
        else return Result.error("未知错误!");
    }

    @GetMapping
    public Result<?> checkLogin(@RequestParam String account,@RequestParam String pwd){
        UserInfo userInfo = authenticationService.checkLogin(new UserAccount(account, pwd));
        if(userInfo == null)return Result.error("账号名或密码错误!");
        else return Result.success("登陆成功!");
    }

    @PutMapping("/pwd")
    public Result<?> changePwd(@RequestParam String account,@RequestParam String pwd,
                               @RequestParam String newPwd){
        int res = authenticationService.changePwd(new UserAccount(account, pwd), newPwd);
        if(res == 1)return Result.success("修改成功!");
        else if(res == 0)return Result.error("账号名或密码错误!");
        else return Result.error("未知错误!");
    }

    @GetMapping("/{id}")
    public Result<?> getUser(@PathVariable Integer id){
        UserInfo userById = authenticationService.getUserById(id);
        if(userById == null)return Result.error("不存在该账户");
        else return Result.success(userById,"查询成功!");
    }

    @PutMapping("/info")
    public Result<?> changeInfo(@RequestBody UserInfo userInfo){
        int res = authenticationService.changeBasicInfo(userInfo);
        if(res == 1)return Result.success("修改成功!");
        else return Result.error("修改失败!");
    }

    @PostMapping("/address")
    public Result<?> addAddress(@RequestBody Address address){
        int res = authenticationService.addAddress(address);
        if(res == 1)return Result.success("添加成功!");
        else return Result.error("添加失败!");
    }

    @PutMapping("/address")
    public Result<?> changeAddress(@RequestBody Address address){
        int res = authenticationService.changeAddress(address);
        if(res == 1)return Result.success("修改成功!");
        else return Result.error("修改失败!");
    }


    @Autowired
    AuthenticationService authenticationService;
}
