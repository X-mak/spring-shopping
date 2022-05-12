package com.shopping.inferior.management.postSale.controller;

import com.github.pagehelper.PageInfo;
import com.shopping.common.Result;
import com.shopping.entity.management.PostSale;
import com.shopping.inferior.management.postSale.service.PostSaleService;
import com.shopping.utils.Authority;
import io.github.yedaxia.apidocs.ApiDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requisition")
public class PostSaleController {

    /**
     * 新增售后申请单
     * @param postSale  申请单信息
     */
    @ApiDoc(result = Result.class)
    @PostMapping("")
    public Result<?> addRequisition(@RequestBody PostSale postSale){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = postSaleService.addRequisitionForm(postSale);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("提交成功!");
    }

    /**
     * 删除售后申请单
     * @param id    申请单主键
     */
    @ApiDoc(result = Result.class)
    @DeleteMapping("")
    public Result<?> deleteRequisition(@RequestParam Integer id){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = postSaleService.deleteRequisitionForm(id);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("删除成功!");
    }

    /**
     * 更新售后申请单
     * @param postSale  申请单信息
     */
    @ApiDoc(result = Result.class)
    @PutMapping("")
    public Result<?> changeRequisition(@RequestBody PostSale postSale){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        int res = postSaleService.changeRequisitionForm(postSale);
        if(res == -1)return Result.error("未知错误!");
        else return Result.success("更新成功!");
    }

    /**
     * 获取申请单列表
     * @param pageNum   页码
     * @param pageSize  单页大小
     * @param buyerId   卖家用户编号
     * @param sellerId  卖家用户编号
     * @param status    申请单状态
     * @return Result<List<PostSale>>
     */
    @ApiDoc
    @GetMapping("list/{pageNum}")
    public Result<List<PostSale>> getRequisition(@PathVariable Integer pageNum,
                                                 @RequestParam(required = false,defaultValue = "10") Integer pageSize,
                                                 @RequestParam(required = false,defaultValue = "") String buyerId,
                                                 @RequestParam(required = false,defaultValue = "") String sellerId,
                                                 @RequestParam(required = false,defaultValue = "%") String status){
        if(!authority.hasRights("buyer"))return Result.error("no way");
        PageInfo<PostSale> postSalePageInfo = new PageInfo<>();
        if(!buyerId.equals("") && sellerId.equals(""))
            postSalePageInfo = postSaleService.getBuyerRequisitionForm(pageNum,pageSize,Integer.parseInt(buyerId),status);
        else if(buyerId.equals("") && !sellerId.equals(""))
            postSalePageInfo = postSaleService.getSellerRequisitionForm(pageNum,pageSize,Integer.parseInt(sellerId),status);
        else
            return Result.error("错误的请求格式!");

        return Result.success(postSalePageInfo.getList(),postSalePageInfo.getTotal()+"");

    }


    @Autowired
    PostSaleService postSaleService;
    @Autowired
    Authority authority;
}
