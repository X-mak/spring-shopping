package com.shopping.inferior.management.postSale.service;

import com.github.pagehelper.PageInfo;
import com.shopping.entity.management.PostSale;

public interface PostSaleService {

    int addRequisitionForm(PostSale postSale);

    int deleteRequisitionForm(Integer id);

    int changeRequisitionForm(PostSale postSale);

    PageInfo<PostSale> getBuyerRequisitionForm(Integer pageNum,Integer pageSize,Integer buyerId,String status);

    PageInfo<PostSale> getSellerRequisitionForm(Integer pageNum,Integer pageSize,Integer sellerId,String status);
}
