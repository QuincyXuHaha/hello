package com.quincy.netty.protocol;

import com.quincy.netty.protocol.req.LoginRequestPacket;
import com.quincy.netty.protocol.req.MsgRequestPacket;
import com.quincy.netty.protocol.resp.LoginResponsePacket;
import com.quincy.netty.protocol.resp.MsgResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

/**
 * 通信对象传输编解码，定义报文格式：
 * <p>
 * =======================================================================================
 * 魔数(int4) | 版本号(byte1) | 序列化算法(byte1) | 指令类型(byte1) | 报文长度(int4) | 报文内容
 * =======================================================================================
 *
 * @author quincy
 * @date 2019/4/9 星期二
 */
public class PacketCodeC {

    /**
     * 魔数
     */
    private static final int MAGIC_NUMBER = 0x12345678;
    private static Map<Byte, Class<? extends AbstractPacket>> requestTypeMap = new HashMap<>();
    private static Map<Byte, Serializer> serializerMap = new HashMap<>();
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    static {
        requestTypeMap.put(Command.LOGIN_REQUEST, LoginRequestPacket.class);
        requestTypeMap.put(Command.LOGIN_RESPONSE, LoginResponsePacket.class);
        requestTypeMap.put(Command.MESSAGE_REQUEST, MsgRequestPacket.class);
        requestTypeMap.put(Command.MESSAGE_RESPONSE, MsgResponsePacket.class);

        serializerMap.put(SerializeAlgorithm.JSON, Serializer.DEFAULT);
    }

    public ByteBuf encode(ByteBufAllocator allocator, AbstractPacket packet) {
        // 1、创建一个buf
        ByteBuf buf = allocator.ioBuffer();
        // 2、序列化对象
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        // 3、按照报文格式写入buf中
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getSerializeAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        return buf;
    }

    public AbstractPacket decode(ByteBuf buf) {
        // 1、魔数
        int magic = buf.readInt();
        // 2、版本号
        byte version = buf.readByte();
        // 3、算法
        byte algorithm = buf.readByte();
        // 4、指令
        byte command = buf.readByte();
        // 5、长度
        int len = buf.readInt();
        // 6、内容
        byte[] data = new byte[len];
        buf.readBytes(data);
        Serializer serializer = getSerializer(algorithm);
        Class<? extends AbstractPacket> requestType = getRequestType(command);
        if (serializer != null && requestType != null) {
            return serializer.deserialize(requestType, data);
        }
        return null;
    }

    /**
     * 获取序列化方式
     *
     * @param algorithm 算法
     * @return 序列化
     */
    private Serializer getSerializer(byte algorithm) {
        return serializerMap.get(algorithm);
    }

    /**
     * 获取请求类型
     *
     * @return 请求类型
     */
    private Class<? extends AbstractPacket> getRequestType(byte command) {
        return requestTypeMap.get(command);
    }

}
