# 云购	--后端工程



## 项目介绍



### 简介

云购是一个线上的购物商城，为买家和卖家提供一个线上交易平台。



### 系统环境

- 编程语言:java

- 使用框架:spring-boot

- 依赖:

  - ```
    japidocs
    ```

  - ```
    redis
    ```

  - ```
    pagehelper
    ```

  - ```
    lombok
    ```

  - ```
    java-jwt
    ```

  - ```
    mysql
    ```

  - ```
    tk.mybatis
    ```

  - ```
    cn.hutool
    ```

  - ```
    spring-boot-starter-web
    ```



### 持久层类图分析

- 总览

![image-20220309194919381](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309194919381.png)





- 用户安全控制

![image-20220309195010507](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195010507.png)





- 用户信息

![image-20220309195109706](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195109706.png)





- 商品信息

![image-20220309195211463](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195211463.png)





- 事务相关

![image-20220309195315403](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195315403.png)







### 接口一览

- 接口文档地址:/docs/V1.0/index.html

- 用户安全接口

![image-20220309195525329](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195525329.png)

- 文件接口

![image-20220309195714120](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195714120.png)

- 商品接口

![image-20220309195829485](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195829485.png)

- 购物车接口

![image-20220309195842359](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195842359.png)

- 收藏品接口

![image-20220309195853740](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195853740.png)

- 订单接口

![image-20220309195905654](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195905654.png)

- 用户信息接口

![image-20220309195926704](C:\Users\23108\AppData\Roaming\Typora\typora-user-images\image-20220309195926704.png)

