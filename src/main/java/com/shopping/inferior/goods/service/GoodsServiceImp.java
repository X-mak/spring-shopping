package com.shopping.inferior.goods.service;

import cn.hutool.core.date.DateUtil;
import com.shopping.entity.goods.*;
import com.shopping.entity.management.ShopGoods;
import com.shopping.mapper.goods.*;
import com.shopping.mapper.management.ShopGoodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService{

    public int addGoods(GoodsInfo goodsInfo){
        try{
            Goods goods = new Goods(goodsInfo.getGoodsName(),goodsInfo.getPrice(),goodsInfo.getStock(),1);
            goodsMapper.insertSelective(goods);
            goodsClassMapper.insertSelective(new GoodsClass(goods.getId(),goodsInfo.getClassId()));
            stockMapper.insertSelective(new Stock(goods.getId(),goodsInfo.getStock()));
            priceMapper.insertSelective(new Price(goods.getId(),goodsInfo.getPrice()));
            shopGoodsMapper.insertSelective(new ShopGoods(goodsInfo.getShopId(),goods.getId()));
            for(String address : goodsInfo.getGoodsPictures()){
                picturesMapper.insertSelective(new Pictures(goods.getId(),address));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }



    public int deleteGoods(Integer goodsId){
        try{
            Example example = new Example(GoodsClass.class);
            example.createCriteria().andEqualTo("goodsId",goodsId);
            goodsClassMapper.deleteByExample(example);
            example = new Example(Stock.class);
            stockMapper.deleteByExample(example);
            example = new Example(Price.class);
            priceMapper.deleteByExample(example);
            example = new Example(Pictures.class);
            picturesMapper.deleteByExample(example);
            example = new Example(ShopGoods.class);
            shopGoodsMapper.deleteByExample(example);
            goodsMapper.deleteByPrimaryKey(goodsId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }



    public int changeGoodsBasicInfo(Goods goods){
        try{
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public Goods getGoods(Integer goodsId){
        return goodsMapper.selectByPrimaryKey(goodsId);
    }


    public List<Goods> getGoodsBySearch(Integer pageNum, Integer pageSize, String keyword, String order,
                                        String classId,Integer shopId){
        return null;
    }


    public int changePrice(Price price){
        try{
            price.setDate(DateUtil.now());
            priceMapper.insertSelective(price);
            Goods goods = new Goods();
            goods.setId(price.getGoodsId());goods.setPrice(price.getPrice());
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public int changeStock(Stock stock){
        try{
            stock.setDate(DateUtil.now());
            stockMapper.insertSelective(stock);
            Goods goods = new Goods();
            goods.setId(stock.getId());goods.setStock(stock.getNum());
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public int changeClass(GoodsClass goodsClass){
        try{
            Example example = new Example(GoodsClass.class);
            example.createCriteria().andEqualTo("goodsId",goodsClass.getGoodsId());
            goodsClassMapper.updateByExampleSelective(goodsClass,example);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public List<Classes> getClasses(String classId){
        if(classId == null || classId.equals("")){
            classId = "__";
        }else{
            classId += "__";
        }
        return classesMapper.queryClasses(classId);
    }


    public int addPictures(Integer goodsId,List<String> pictures){
        try{
            for(String address : pictures){
                picturesMapper.insertSelective(new Pictures(goodsId,address));
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }

    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    GoodsClassMapper goodsClassMapper;
    @Autowired
    ClassesMapper classesMapper;
    @Autowired
    PicturesMapper picturesMapper;
    @Autowired
    PriceMapper priceMapper;
    @Autowired
    StockMapper stockMapper;
    @Autowired
    ShopGoodsMapper shopGoodsMapper;
}
