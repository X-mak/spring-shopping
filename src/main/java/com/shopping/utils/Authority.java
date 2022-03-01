package com.shopping.utils;

import com.shopping.entity.authentication.AccountRole;
import com.shopping.entity.authentication.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class Authority {

    public boolean hasRights(String role){

        //获取角色编号
        int role_id = 0;
        if(role.equals("buyer")){
            role_id = 1;
        }else if(role.equals("seller")){
            role_id = 2;
        }else if(role.equals("admin")){
            role_id = 3;
        }
        UserInfo loginUser = TokenUtils.getLoginUser();
        if(loginUser==null)return false;
        for(AccountRole accountRole : loginUser.getRoleList()){
            if(accountRole.getRoleId() == role_id)return true;
        }
        return false;
    }
}
