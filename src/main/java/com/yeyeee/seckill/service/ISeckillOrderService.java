package com.yeyeee.seckill.service;

import com.yeyeee.seckill.entity.SeckillOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyeee.seckill.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {
    /*获取秒杀结果
    * 成功or秒杀失败(-1)or排队中(0)*/
    Long getResult (User user, Long goodsId);
}
