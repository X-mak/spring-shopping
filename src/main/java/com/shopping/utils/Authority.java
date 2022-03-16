package com.shopping.utils;

import com.shopping.entity.authentication.AccountRole;
import com.shopping.entity.authentication.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class Authority {

    public boolean hasRights(String role){

        //获取角色编号
        int roleId = 0;
        if(role.equals("buyer")){
            roleId = 1;
        }else if(role.equals("seller")){
            roleId = 2;
        }else if(role.equals("admin")){
            roleId = 3;
        }
        UserInfo loginUser = TokenUtils.getLoginUser();
        if(loginUser==null)return false;
        for(AccountRole accountRole : loginUser.getRoleList()){
            if(accountRole.getRoleId() == roleId)return true;
        }
        return false;
    }
}
