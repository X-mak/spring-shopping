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
//                Example example = new Example(UserGoods.class);
//                example.createCriteria().andEqualTo("userId",id)
//                        .andEqualTo("goodsId",c.getGoodsId())
//                        .andEqualTo("propertyId",1);
//                List<UserGoods> userGoods = userGoodsMapper.selectByExample(example);
//                if(userGoods.size()>0)continue;
                userGoodsMapper.insertSelective(new UserGoods(id,c.getGoodsId(),1,null));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }



    public int cancelMultiCollect(List<Integer> collects){
        try{
            for(Integer id : collects){
                if(!accessControlUtil.controlInId(id,1))return -1;

                userGoodsMapper.deleteByPrimaryKey(id);
            }
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
        Example example = new Example(UserGoods.class);
        try{
            example.createCriteria().andEqualTo("userId",id)
                    .andEqualTo("goodsId",goodsId)
                    .andEqualTo("propertyId",1);
            List<UserGoods> userGoods = userGoodsMapper.selectByExample(example);
            if(userGoods.size()>0)
                return userGoods.get(0).getId();
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
