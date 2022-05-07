package com.shopping.inferior.management.collections.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.data.UserGoods;
import com.shopping.entity.management.CollectedItem;
import com.shopping.mapper.data.UserGoodsMapper;
import com.shopping.mapper.goods.GoodsMapper;
import com.shopping.mapper.management.CollectedItemMapper;
import com.shopping.utils.AccessControlUtil;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class CollectionsServiceImp implements CollectionsService{

    public int collectMany(List<CollectedItem> collectedList){
        try{
            for(CollectedItem c : collectedList){
                Integer id = TokenUtils.getLoginUser().getId();
                userGoodsMapper.insertSelective(new UserGoods(id,c.getGoodsId(),1,null));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public int cancelCollect(Integer id){
        try{
            if(!accessControlUtil.controlInId(id,1))return -1;

            userGoodsMapper.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    public PageInfo<CollectedItem> getSelectedCollections(Integer pageNum,Integer pageSize){
        Integer id = TokenUtils.getLoginUser().getId();
        PageHelper.startPage(pageNum,pageSize,true);
        List<CollectedItem> collectedItems = collectedItemMapper.queryCollectedById(id);
        return new PageInfo<>(collectedItems);
    }

    public int isCollected(Integer goodsId){
        Integer id = TokenUtils.getLoginUser().getId();
        Example example = new Example(CollectedItem.class);
        try{
            example.createCriteria().andEqualTo("userId",id).andEqualTo("goodsId",goodsId);
            List<CollectedItem> collectedItems = collectedItemMapper.selectByExample(example);
            if(collectedItems.size()>0)
                return collectedItems.get(0).getId();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    @Autowired
    CollectedItemMapper collectedItemMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    UserGoodsMapper userGoodsMapper;
    @Autowired
    AccessControlUtil accessControlUtil;
}
