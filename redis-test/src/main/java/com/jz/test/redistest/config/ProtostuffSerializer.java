package com.jz.test.redistest.config;

import com.alibaba.fastjson.JSONObject;
import com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.UnsupportedEncodingException;

/**
 * ProtoStuff序列化工具
 * @author LinkinStar
 */
public class ProtostuffSerializer implements RedisSerializer<Object> {

    private boolean isEmpty(byte[] data) {
        return (data == null || data.length == 0);
    }

    private final Schema<ProtoWrapper> schema;

    private final ProtoWrapper wrapper;

    private final LinkedBuffer buffer;

    public ProtostuffSerializer() {
        this.wrapper = new ProtoWrapper();
        this.schema = RuntimeSchema.getSchema(ProtoWrapper.class);
        this.buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    }


    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        wrapper.data = t;
        try {
            return ProtostuffIOUtil.toByteArray(wrapper, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    public Object deserialize(byte[] bytes) throws SerializationException {
        if (isEmpty(bytes)) {
            return null;
        }
        ProtoWrapper newMessage = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, newMessage, schema);
        return newMessage.data;
    }

    private static class ProtoWrapper {
        private Object data;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String jsonStr = "{\"@class\":\"com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO\",\"vehicleNo\":\"H305\",\"longitude\":121.64361379499793,\"latitude\":31.338566040509672,\"vehicleLoadState\":\"1\",\"vehicleState\":\"2\",\"vehicleArea\":\"XQ13\",\"vehicleDirection\":null,\"vehicleSpeed\":0.29,\"vehicleType\":\"ITK\",\"vehicleDrivingState\":\"1\",\"vehicleLane\":\"1657324672539165008\",\"trkServeradd\":\"localhost:8090\",\"heading\":\"296.82\",\"targetAreaDistance\":null,\"vehicleRoad\":\"721138899999523152\",\"scopeCoordinate\":\"121.64353025194688,31.338589154619072 121.64354257011628,31.338610075359753 121.64369733804894,31.338542926400265 121.64368501987957,31.33852200565956\",\"laneNum\":1,\"uwbTask\":\"2\",\"targetAreaCode\":null,\"acceleratedSpeed\":161.35,\"angularSpeed\":-16.01,\"lastlat\":[\"java.util.LinkedList\",[\"121.64998477663563 31.336387681896532 1629202479671\",\"121.6499815664367 31.336389441665624 1629202480078\",\"121.64997873627931 31.336390801463043 1629202480087\",\"121.64361339503026 31.338565650484103 1629253926598\",\"121.64361379499793 31.338566040509672 1629253926719\"]],\"energyLevel\":null,\"mapId\":null,\"idType\":null,\"curTime\":1629253926719}";
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO = JSONObject.parseObject(jsonStr, IecsAivVehicInfoDTO.class);
        ProtostuffSerializer protostuffSerializer = new ProtostuffSerializer();
        byte[] serialize = protostuffSerializer.serialize(iecsAivVehicInfoDTO);
        Object deserialize = protostuffSerializer.deserialize(serialize);
        System.out.println(deserialize);


        String s2="\\x0b\\xfa\\x07*com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO\\x0a\\x04H305\\x11Q1\\xea\\xf70i^@\\x19w\\x86\\x97C\\xacV?@\"\\x011*\\x0122\\x04XQ13A\\x8f\\xc2\\xf5(\\\\x8f\\xd2?J\\x03ITKR\\x011Z\\x131657324672539165008b\\x0elocalhost:8090j\\x06296.82z\\x12721138899999523152\\x82\\x01\\x96\\x01121.643530a25194688,31.338589154619072 121.64354257011628,31.338610075359753 121.64369733804894,31.338542926400265 121.64368501987957,31.33852200565956\\x88\\x01\\x01\\x92\\x01\\x012\\xa1\\x0133333+d@\\xa9\\x01\\xc3\\xf5(\\\\x8f\\x020\\xc0\\xb2\\x01\\x14java.util.LinkedList\\xb2\\x01\\x8e\\x02[\"121.64998477663563 31.336387681896532 1629202479671\",\"121.6499815664367 31.336389441665624 1629202480078\",\"121.64997873627931 31.336390801463043 1629202480087\",\"121.64361339503026 31.338565650484103 1629253926598\",\"121.64361379499793 31.338566040509672 1629253926719\"]\\xd0\\x01\\xbf\\xfe\\xe7\\xb8\\xb5/\\x0c";
        byte[] bytes1 = s2.getBytes();

        Object deserialize1 = protostuffSerializer.deserialize(bytes1);
        System.out.println(deserialize1);

    }
}