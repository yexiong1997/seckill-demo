package com.yeyeee.seckill.service;

import com.yeyeee.seckill.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yeyeee.seckill.vo.LoginVo;
import com.yeyeee.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-05
 */
public interface IUserService extends IService<User> {

    RespBean doLogin (LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

//根据cookie获取用户
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);

//更新密码
    RespBean updatePassword(String userTicket,String password,HttpServletRequest request,HttpServletResponse response);
}
