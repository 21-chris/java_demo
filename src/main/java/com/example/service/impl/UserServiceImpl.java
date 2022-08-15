package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.common.Result;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public User findUserById(int id) {
        return  userMapper.selectById(id);
    }

    @Override
    public User findUserByName(User user) {
        LambdaQueryWrapper<User> queryWrapper =  new LambdaQueryWrapper<>();
        return  userMapper.selectOne(queryWrapper);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public Result loginUser(HttpServletRequest request, User user) {
//        获取用户密码对比
        LambdaQueryWrapper<User> queryWrapper =  new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,user.getName());
        User oneQuery =  userMapper.selectOne(queryWrapper);
        Result result1 = new Result("200","true","登录成功");
        if(oneQuery.getPassword().equals( user.getPassword())){
            request.getSession().setAttribute("user",oneQuery.getId());
            return  result1;
        }else{
            result1.setData("密码错误");
            int time = 1;
            //获取redis里面的用户id存储次数，如果首次就存一次
            String times = String.valueOf(redisTemplate.opsForValue().get(String.valueOf(oneQuery.getId())));
            if(times == "null"){
                redisTemplate.opsForValue().set(oneQuery.getId().toString(),String.valueOf(time), 1, TimeUnit.MINUTES);
                times = String.valueOf(redisTemplate.opsForValue().get(String.valueOf(user.getId())));
            }
            System.out.println(times);
//            获取过期时间
            long expire =  redisTemplate.getExpire(String.valueOf(user.getId()));

            if (StringUtils.isNotBlank(times)) {
                time = Integer.parseInt(times) + 1;
            }
            redisTemplate.opsForValue().set(user.getId().toString(),String.valueOf(time), Long.parseLong(Long.toString(expire)), TimeUnit.MINUTES);
            //密码错误5次
            if (StringUtils.isNotBlank(times) && Integer.parseInt(times) >= 5) {
                result1.setData("密码被锁定1分钟");
            }
            return  result1;

        }
    }
}
