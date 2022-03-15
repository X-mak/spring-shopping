package com.shopping.inferior.authentication.controller;

import com.shopping.common.Result;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.RegisterUser;
import com.shopping.entity.authentication.UserAccount;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.inferior.authentication.service.AuthenticationService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    /**
     *  用户注册
     * @param registerUser 获取方式
     *
     */
    @ApiDoc(result = Result.class)
    @PostMapping
    public Result<?> addUser(@RequestBody RegisterUser registerUser){
        int res = authenticationService.addUser(registerUser);
        if(res == 1)return Result.success("注册成功!");
        else if(res == 0)return Result.error("存在重复账号!");
        else return Result.error("未知错误!");
    }

    /**
     *  用户登录
     * @param account 用户账号
     * @param pwd   用户密码
     * @return Result<UserInfo>
     */
    @ApiDoc
    @GetMapping
    public Result<UserInfo> checkLogin(@RequestParam String account,@RequestParam String pwd){
        UserInfo userInfo = authenticationService.checkLogin(new UserAccount(account, pwd));
        if(userInfo == null)return Result.error("账号名或密码错误!");
        else return Result.success(userInfo,"登陆成功!");
    }

    /**
     * 用户修改密码
     * @param account   用户账号
     * @param pwd   用户原密码
     * @param newPwd    用户新密码
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/pwd")
    public Result<?> changePwd(@RequestParam String account,@RequestParam String pwd,
                               @RequestParam String newPwd){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = authenticationService.changePwd(new UserAccount(account, pwd), newPwd);
        if(res == 1)return Result.success("修改成功!");
        else if(res == 0)return Result.error("账号名或密码错误!");
        else return Result.error("未知错误!");
    }


    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    Authority authority;
}
