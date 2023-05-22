package com.yeyeee.seckill.service;

import com.yeyeee.seckill.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyeee.seckill.entity.User;
import com.yeyeee.seckill.vo.GoodsVo;
import com.yeyeee.seckill.vo.OrderDetailVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */
public interface IOrderService extends IService<Order> {

    Order seckill (User user, GoodsVo goods);

    OrderDetailVo detail (Long orderId);

    /*获取秒杀地址*/
    String createPath (User user, Long goodsId);
    /*校验秒杀地址*/
    boolean checkPath (User user, Long goodsId, String path);
    /*校验验证码*/
    boolean checkCaptcha (User user, Long goodsId, String captcha);
}
