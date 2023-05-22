package com.yeyeee.seckill.service.impl;

import com.yeyeee.seckill.entity.Goods;
import com.yeyeee.seckill.mapper.GoodsMapper;
import com.yeyeee.seckill.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeyeee.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> findGoodsVo () {
        return goodsMapper.findGoodsVo();
    }

    @Override
    public GoodsVo findGoodsVoByGoodsId (Long goodsId) {
        return goodsMapper.findGoodsVoByGoodsId(goodsId);
    }
}
