package com.yeyeee.seckill.service;

import com.yeyeee.seckill.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyeee.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
