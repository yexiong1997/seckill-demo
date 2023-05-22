package com.yeyeee.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yeyeee.seckill.entity.User;
import com.yeyeee.seckill.exception.GlobalException;
import com.yeyeee.seckill.mapper.UserMapper;
import com.yeyeee.seckill.service.IUserService;
import com.yeyeee.seckill.utils.CookieUtil;
import com.yeyeee.seckill.utils.MD5Util;
import com.yeyeee.seckill.utils.UUIDUtil;
import com.yeyeee.seckill.vo.LoginVo;
import com.yeyeee.seckill.vo.RespBean;
import com.yeyeee.seckill.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yexiong
 * @since 2022-12-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public RespBean doLogin (LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
//        //参数校验
//        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password)){
//            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
//        }
//        if (!ValidatorUtil.isMobile(mobile)){
//            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
//        }

//      根据手机号获取用户
        User user = userMapper.selectById(mobile);
        if (null==user){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//      判断密码是否正确
        if (!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){
            throw new GlobalException(RespBeanEnum.LOGIN_ERROR);
        }
//        生成cookie
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user:" + ticket,user);
//        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);
    }
//根据Cookie获取用户对象
    @Override
    public User getUserByCookie (String userTicket,HttpServletRequest request,HttpServletResponse response) {
        if (StringUtils.isEmpty(userTicket)){
            return null;
        }
        User user = (User) (redisTemplate.opsForValue().get("user:" + userTicket));
        if (user != null){
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
//更新密码
    @Override
    public RespBean updatePassword (String userTicket, String password,HttpServletRequest request,HttpServletResponse response) {
        User user = getUserByCookie(userTicket, request, response);
        if (user==null){
            throw new GlobalException(RespBeanEnum.MOBILE_NOT_EXIST);
        }
        user.setPassword(MD5Util.inputPassToDBPass(password,user.getSalt()));
        int result = userMapper.updateById(user);
        if (1==result){
            redisTemplate.delete("user:"+userTicket);
            return RespBean.success();
        }
        return RespBean.error(RespBeanEnum.PASSWORD_UPDATE_FAIL);
    }
}
