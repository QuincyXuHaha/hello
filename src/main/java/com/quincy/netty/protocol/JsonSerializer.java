package com.quincy.netty.protocol;

import com.alibaba.fastjson.JSON;

/**
 * fastjson序列化实现
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
public class JsonSerializer implements Serializer {
    @Override
    public Byte getSerializeAlgorithm() {
        return SerializeAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object o) {
        return JSON.toJSONBytes(o);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
