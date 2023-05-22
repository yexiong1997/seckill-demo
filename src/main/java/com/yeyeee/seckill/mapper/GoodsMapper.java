package com.yeyeee.seckill.mapper;

import com.yeyeee.seckill.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yeyeee.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */

public interface GoodsMapper extends BaseMapper<Goods> {

    List<GoodsVo> findGoodsVo ();

    GoodsVo findGoodsVoByGoodsId (Long goodsId);
}
