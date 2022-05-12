package com.shopping.inferior.management.postSale.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.PostSale;
import com.shopping.mapper.management.PostSaleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostSaleServiceImp implements PostSaleService{

    public int addRequisitionForm(PostSale postSale){
        try{
            postSale.setStatus(0);
            postSaleMapper.insertSelective(postSale);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int deleteRequisitionForm(Integer id){
        try{
            postSaleMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int changeRequisitionForm(PostSale postSale){
        try{
            postSaleMapper.updateByPrimaryKeySelective(postSale);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<PostSale> getBuyerRequisitionForm(Integer pageNum, Integer pageSize,
                                                      Integer buyerId, String status){
        PageHelper.startPage(pageNum,pageSize,true);
        List<PostSale> postSales = postSaleMapper.queryPostSalesByBuyer(buyerId, status);
        return new PageInfo<>(postSales);
    }

    public PageInfo<PostSale> getSellerRequisitionForm(Integer pageNum,Integer pageSize,
                                                       Integer sellerId,String status){
        PageHelper.startPage(pageNum,pageSize,true);
        List<PostSale> postSales = postSaleMapper.queryPostSalesBySeller(sellerId, status);
        return new PageInfo<>(postSales);
    }




    @Autowired
    PostSaleMapper postSaleMapper;
}
