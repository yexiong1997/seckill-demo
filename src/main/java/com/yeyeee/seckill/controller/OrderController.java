package com.yeyeee.seckill.controller;

import com.yeyeee.seckill.entity.User;
import com.yeyeee.seckill.service.IOrderService;
import com.yeyeee.seckill.vo.OrderDetailVo;
import com.yeyeee.seckill.vo.RespBean;
import com.yeyeee.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yexiong
 * @since 2022-12-12
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @RequestMapping("/detail")
    @ResponseBody
    public RespBean detail(User user,Long orderId){
        if (user==null){
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        OrderDetailVo detail = orderService.detail(orderId);
        return RespBean.success(detail);
    }



}
