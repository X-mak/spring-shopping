package com.shopping.entity.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "accountrole")
public class AccountRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键，关联类编号

    private Integer roleId; //角色编号
    private Integer accountId;  //账号编号

    public AccountRole(Integer roleId, Integer accountId) {
        this.roleId = roleId;
        this.accountId = accountId;
    }
}
