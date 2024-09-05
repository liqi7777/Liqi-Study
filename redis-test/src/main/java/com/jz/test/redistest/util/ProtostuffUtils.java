package com.jz.test.redistest.util;

import com.alibaba.fastjson.JSONObject;
import com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liqi
 * create  2021-08-19 17:00
 */
public class ProtostuffUtils {
    /**
     * 避免每次序列化都重新申请Buffer空间
     */
    private static LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    /**
     * 缓存Schema
     */
    private static Map<Class<?>, Schema<?>> schemaCache = new ConcurrentHashMap<>();

    /**
     * 序列化方法，把指定对象序列化成字节数组
     *
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> clazz = (Class<T>) obj.getClass();
        Schema<T> schema = getSchema(clazz);
        byte[] data;
        try {
            data = ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } finally {
            buffer.clear();
        }

        return data;
    }

    /**
     * 反序列化方法，将字节数组反序列化成指定Class类型
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> clazz) {
        Schema<T> schema = getSchema(clazz);
        T obj = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(data, obj, schema);
        return obj;
    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) schemaCache.get(clazz);
        if (Objects.isNull(schema)) {
            //这个schema通过RuntimeSchema进行懒创建并缓存
            //所以可以一直调用RuntimeSchema.getSchema(),这个方法是线程安全的
            schema = RuntimeSchema.getSchema(clazz);
            if (Objects.nonNull(schema)) {
                schemaCache.put(clazz, schema);
            }
        }

        return schema;
    }

    public static void main(String[] args) {
        String jsonStr = "{\"@class\":\"com.jz.iecs.entity.DTO.IecsAivVehicInfoDTO\",\"vehicleNo\":\"H305\",\"longitude\":121.64361379499793,\"latitude\":31.338566040509672,\"vehicleLoadState\":\"1\",\"vehicleState\":\"2\",\"vehicleArea\":\"XQ13\",\"vehicleDirection\":null,\"vehicleSpeed\":0.29,\"vehicleType\":\"ITK\",\"vehicleDrivingState\":\"1\",\"vehicleLane\":\"1657324672539165008\",\"trkServeradd\":\"localhost:8090\",\"heading\":\"296.82\",\"targetAreaDistance\":null,\"vehicleRoad\":\"721138899999523152\",\"scopeCoordinate\":\"121.64353025194688,31.338589154619072 121.64354257011628,31.338610075359753 121.64369733804894,31.338542926400265 121.64368501987957,31.33852200565956\",\"laneNum\":1,\"uwbTask\":\"2\",\"targetAreaCode\":null,\"acceleratedSpeed\":161.35,\"angularSpeed\":-16.01,\"lastlat\":[\"java.util.LinkedList\",[\"121.64998477663563 31.336387681896532 1629202479671\",\"121.6499815664367 31.336389441665624 1629202480078\",\"121.64997873627931 31.336390801463043 1629202480087\",\"121.64361339503026 31.338565650484103 1629253926598\",\"121.64361379499793 31.338566040509672 1629253926719\"]],\"energyLevel\":null,\"mapId\":null,\"idType\":null,\"curTime\":1629253926719}";
        IecsAivVehicInfoDTO iecsAivVehicInfoDTO = JSONObject.parseObject(jsonStr, IecsAivVehicInfoDTO.class);
        byte[] serialize = ProtostuffUtils.serialize(iecsAivVehicInfoDTO);
        IecsAivVehicInfoDTO deserialize = ProtostuffUtils.deserialize(serialize, IecsAivVehicInfoDTO.class);
        System.out.println(deserialize);
    }
}
