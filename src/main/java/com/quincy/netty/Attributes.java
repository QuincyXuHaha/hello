package com.quincy.netty;

import io.netty.util.AttributeKey;

/**
 * 属性
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
public interface Attributes {
    /**
     * 登录属性
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");


}
