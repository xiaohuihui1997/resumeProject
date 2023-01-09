package com.wjz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wjz.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wjz
 * @since 2022-11-11
 */
public interface UserService extends IService<User> {

    List<User> queryAll();
}
