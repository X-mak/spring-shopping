package com.shopping.inferior.management.collections.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.goods.Goods;
import com.shopping.entity.management.Collections;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.CollectionsMapper;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CollectionsServiceImp implements CollectionsService{

    public int collectMany(List<Collections> collectionsList){
        try{
            for(Collections c : collectionsList){
                collectionsMapper.insertSelective(c);
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int cancelCollect(Integer id){
        try{
            collectionsMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<Goods> getSelectedCollections(Integer pageNum,Integer pageSize){
        Integer id = TokenUtils.getLoginUser().getId();
        PageHelper.startPage(pageNum,pageSize,true);
        List<Goods> goods = goodsMapper.queryGoodsInCollections(id);
        return new PageInfo<>(goods);
    }

    @Autowired
    CollectionsMapper collectionsMapper;
    @Autowired
    GoodsMapper goodsMapper;
}
