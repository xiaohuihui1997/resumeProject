package com.wjz.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjz.entity.User;
import com.wjz.mapper.UserMapper;
import com.wjz.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> queryAll() {
        return userMapper.selectList(null);
    }
}
