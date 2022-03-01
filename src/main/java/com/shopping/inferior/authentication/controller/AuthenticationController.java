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
@RequestMapping("/user")
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

    /**
     * 获取用户信息
     * @param id    用户编号
     * @return  Result<UserInfo>
     */
    @ApiDoc
    @GetMapping("/{id}")
    public Result<UserInfo> getUser(@PathVariable Integer id){
        UserInfo userById = authenticationService.getUserById(id);
        if(userById == null)return Result.error("不存在该账户");
        else return Result.success(userById,"查询成功!");
    }

    /**
     * 修改用户信息
     * @param userInfo  用户信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/info")
    public Result<?> changeInfo(@RequestBody UserInfo userInfo){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = authenticationService.changeBasicInfo(userInfo);
        if(res == 1)return Result.success("修改成功!");
        else return Result.error("修改失败!");
    }

    /**
     * 新增地址
     * @param address   地址信息
     */
    @ApiDoc(result = Result.class)
    @PostMapping("/address")
    public Result<?> addAddress(@RequestBody Address address){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = authenticationService.addAddress(address);
        if(res == 1)return Result.success("添加成功!");
        else return Result.error("添加失败!");
    }

    /**
     * 修改地址
     * @param address   地址信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("/address")
    public Result<?> changeAddress(@RequestBody Address address){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = authenticationService.changeAddress(address);
        if(res == 1)return Result.success("修改成功!");
        else return Result.error("修改失败!");
    }


    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    Authority authority;
}
