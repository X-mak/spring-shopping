package com.shopping.inferior.management.user.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.authentication.Address;
import com.shopping.entity.authentication.UserInfo;
import com.shopping.inferior.management.user.service.UserService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取用户信息
     * @param id    用户编号
     * @return  Result<UserInfo>
     */
    @ApiDoc
    @GetMapping("/{id}")
    public Result<UserInfo> getUser(@PathVariable Integer id){
        UserInfo userById = userService.getUserById(id);
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
        int res = userService.changeBasicInfo(userInfo);
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
        int res = userService.addAddress(address);
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
        int res = userService.changeAddress(address);
        if(res == 1)return Result.success("修改成功!");
        else return Result.error("修改失败!");
    }


    /**
     * 获取用户地址列表
     * @param pageNum   页码
     * @param userId    用户编号
     * @param pageSize  单页数量
     * @return  Result<List<Address>>
     */
    @ApiDoc
    @GetMapping("/address/list/{pageNum}")
    public Result<List<Address>> getAddressList(@PathVariable Integer pageNum,
                                                @RequestParam Integer userId,
                                                @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        PageInfo<Address> address = userService.getAddress(userId, pageNum, pageSize);
        if(address.getTotal() == 0)return Result.error("查询结果为空!");
        return Result.success(address.getList(),address.getTotal()+"");
    }


    /**
     * 删除地址
     * @param id    地址编号
     */
    @ApiDoc(result = Result.class)
    @DeleteMapping("/address")
    public Result<?> deleteAddress(@RequestParam Integer id){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = userService.deleteAddress(id);
        if(res == -1)return Result.error("删除失败!");
        if(res == 0)return Result.error("您不能删除默认地址!");
        return Result.success("删除成功!");
    }


    /**
     * 获取用户列表
     * @param pageNum   页码
     * @param pageSize  单页数量
     * @return  Result<List<UserInfo>>
     */
    @ApiDoc
    @GetMapping("/list/{pageNum}")
    public Result<List<UserInfo>> getUserList(@PathVariable Integer pageNum,
                                              @RequestParam(required = false,defaultValue = "10") Integer pageSize){
        if(!authority.hasRights("seller"))return Result.error("no way");
        PageInfo<UserInfo> users = userService.getUsers(pageNum, pageSize);
        if(users.getTotal() == 0)return Result.error("查询结果为空!");
        return Result.success(users.getList(),users.getTotal()+"");
    }

    /**
     * 禁用账号
     * @param userId    被禁用账户编号
     */
    @ApiDoc(result = Result.class)
    @DeleteMapping("/{userId}")
    public Result<?> banUser(@PathVariable Integer userId){
        if(!authority.hasRights("admin"))return Result.error("no way");
        int res = userService.banUser(userId);
        if(res == -1)return Result.error("封禁失败!");
        return Result.success("封禁成功!");
    }

    @Autowired
    UserService userService;
    @Autowired
    Authority authority;
}
