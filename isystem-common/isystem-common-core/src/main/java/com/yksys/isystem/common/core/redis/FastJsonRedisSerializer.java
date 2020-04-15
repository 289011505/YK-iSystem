package com.yksys.isystem.common.core.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @program: project_base
 * @description:
 * @author: YuKai Fan
 * @create: 2019-06-13 15:44
 **/
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    /**
     * 添加autoType白名单
     * 解决redis反序列化对象时报错 ：com.alibaba.fastjson.JSONException: autoType is not support
     */
    static {
//        ParserConfig.getGlobalInstance().addAccept("com.yksys.isystem.common.pojo.*");
//        ParserConfig.getGlobalInstance().addAccept("com.yksys.isystem.common.model.*");
//        ParserConfig.getGlobalInstance().addAccept("com.yksys.isystem.common.model.message.*");
//        ParserConfig.getGlobalInstance().addAccept("com.yksys.isystem.common.model.register.*");
//        ParserConfig.getGlobalInstance().addAccept("com.yksys.isystem.common.model.server.*");
//        ParserConfig.getGlobalInstance().addAccept("com.yksys.isystem.common.model.tree.*");
//        ParserConfig.getGlobalInstance().addAccept("io.netty.channel.socket.nio.NioSocketChannel");
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }

}