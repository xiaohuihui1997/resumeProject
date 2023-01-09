package com.wjz.utils;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充创建时间和修改时间
 */

@Component
public class MyDateHandler implements MetaObjectHandler {

//    private static final Logger logger = (Logger) LoggerFactory.getLogger(MyDateHandler.class);

    /**
     * 新增数据执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
//        SysUserEntity userEntity = ShiroUtil.getUser();
        this.setFieldValByName("createTime", new Date(), metaObject);
//        this.setFieldValByName("createUser", userEntity.getLoginName(), metaObject);
    }

    /**
     * 更新数据执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
//        SysUserEntity userEntity = ShiroUtil.getUser();
        this.setFieldValByName("updateTime", new Date(), metaObject);
//        this.setFieldValByName("updateBy", userEntity.getLoginName(), metaObject);
    }
}
