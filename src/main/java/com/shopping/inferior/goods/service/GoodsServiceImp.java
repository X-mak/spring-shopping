package com.shopping.inferior.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shopping.entity.data.GoodsEx;
import com.shopping.entity.data.UserEx;
import com.shopping.entity.data.UserGoods;
import com.shopping.entity.goods.*;
import com.shopping.mapper.data.GoodsExMapper;
import com.shopping.mapper.data.UserGoodsMapper;
import com.shopping.mapper.goods.*;
import com.shopping.utils.AccessControlUtil;
import com.shopping.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImp implements GoodsService{

    public int addGoods(GoodsInfo goodsInfo){
        try{
            Goods goods = new Goods(goodsInfo.getGoodsName(),goodsInfo.getPrice(),goodsInfo.getStock(),1,goodsInfo.getIntroduction());
            goodsMapper.insertSelective(goods);
            goodsClassMapper.insertSelective(new GoodsClass(goods.getId(),goodsInfo.getClassId()));
            Integer id = TokenUtils.getLoginUser().getId();

            goodsExMapper.insertSelective(new GoodsEx(goods.getId(),1,goodsInfo.getStock()+"",0));
            goodsExMapper.insertSelective(new GoodsEx(goods.getId(),2,goodsInfo.getPrice()+"",0));
            userGoodsMapper.insertSelective(new UserGoods(id,goods.getId(),3));

//            stockMapper.insertSelective(new Stock(goods.getId(),goodsInfo.getStock()));
//            priceMapper.insertSelective(new Price(goods.getId(),goodsInfo.getPrice()));
            int i = 0;
            for(String address : goodsInfo.getGoodsPictures()){
                if(i == 0)
                    goodsExMapper.insertSelective(new GoodsEx(goods.getId(),3,address,1));
                else
                    goodsExMapper.insertSelective(new GoodsEx(goods.getId(),3,address,0));
                i++;
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
            example = new Example(GoodsEx.class);
            example.createCriteria().andEqualTo("goodsId",goodsId);
            goodsExMapper.deleteByExample(example);
            example = new Example(UserGoods.class);
            example.createCriteria().andEqualTo("goodsId",goodsId);
            userGoodsMapper.deleteByExample(example);
            goodsMapper.deleteByPrimaryKey(goodsId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }



    public int changeGoodsBasicInfo(Goods goods){
        try{
            //if(!accessControlUtil.controlInUserGoods(goods.getId(),3))return -1;
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public Goods getGoods(Integer goodsId){
        return goodsMapper.queryGoodsById(goodsId);
    }


    public PageInfo<Goods> getGoodsBySearch(Integer pageNum, Integer pageSize, String keyword, String order,
                                            String classId,String status){
        List<Goods> goods = new ArrayList<>();
        //处理参数
        if(!keyword.equals("%"))keyword = "%"+keyword+"%";
        if(!classId.equals("%"))classId = classId+"%";
        //分页
        PageHelper.startPage(pageNum,pageSize,true);

        //根据排序方式使用mapper
        if(order.equals("date")){
            goods = goodsMapper.querySelectedGoodsOrderByDate(keyword, classId,status);
        }else if(order.equals("sales")){
            goods = goodsMapper.querySelectedGoodsOrderBySales(keyword, classId,status);
        }

        return new PageInfo<>(goods);
    }


    public int changePrice(Price price){
        try{
            if(!accessControlUtil.controlInUserGoods(price.getGoodsId(),3))return -1;

            GoodsEx goodsEx = new GoodsEx(price.getGoodsId(),2,price.getPrice()+"",0);
            goodsExMapper.insertSelective(goodsEx);
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
            if(!accessControlUtil.controlInUserGoods(stock.getGoodsId(),3))return -1;

            GoodsEx goodsEx = new GoodsEx(stock.getGoodsId(),1,stock.getNum()+"",0);
            goodsExMapper.insertSelective(goodsEx);
            Goods goods = new Goods();
            goods.setId(stock.getGoodsId());goods.setStock(stock.getNum());
            goodsMapper.updateByPrimaryKeySelective(goods);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 1;
    }


    public int changeClass(GoodsClass goodsClass){
        try{
            if(!accessControlUtil.controlInUserGoods(goodsClass.getGoodsId(),3))return -1;

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
                goodsExMapper.insertSelective(new GoodsEx(goodsId,3,address,0));
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
    UserGoodsMapper userGoodsMapper;
    @Autowired
    GoodsExMapper goodsExMapper;
    @Autowired
    AccessControlUtil accessControlUtil;
}
