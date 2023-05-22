package com.yeyeee.seckill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yeyeee.seckill.entity.SeckillOrder;
import com.yeyeee.seckill.entity.User;
import com.yeyeee.seckill.mapper.SeckillOrderMapper;
import com.yeyeee.seckill.service.ISeckillOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder> implements ISeckillOrderService {

   @Autowired
   private SeckillOrderMapper seckillOrderMapper;
   @Autowired
   private RedisTemplate redisTemplate;

    /*获取秒杀结果
     * 成功or秒杀失败(-1)or排队中(0)*/
    @Override
    public Long getResult (User user, Long goodsId) {
        SeckillOrder seckillOrder = seckillOrderMapper.selectOne(new QueryWrapper<SeckillOrder>().eq("user_id",user.getId()).eq("goods_id",goodsId));
        if (null != seckillOrder){
            return seckillOrder.getOrderId();
        } else if (redisTemplate.hasKey("isStockEmpty:"+goodsId)) {
            return -1L;
        }else {
            return 0L;
        }
    }
}
