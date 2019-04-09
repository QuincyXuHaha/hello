package com.quincy.netty.protocol;

/**
 * 对象序列化接口
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
public interface Serializer {

    Serializer DEFAULT = new JsonSerializer();

    /**
     * 序列化算法
     *
     * @return 序列化算法
     */
    Byte getSerializeAlgorithm();

    /**
     * 序列化，即对象转二进制
     *
     * @param o 对象
     * @return 二进制
     */
    byte[] serialize(Object o);

    /**
     * 反序列化，即二进制转对象
     *
     * @param clazz 对象类型
     * @param bytes 二进制
     * @param <T>   对象类型
     * @return 对象
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
