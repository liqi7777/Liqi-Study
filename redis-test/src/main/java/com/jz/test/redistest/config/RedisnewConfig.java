package com.jz.test.redistest.config;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.StringUtils;

import java.net.UnknownHostException;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

// @Configuration
// @EnableCaching
public class RedisnewConfig {


//    @Autowired
//    private RedisProperties redisProperties;
//
//
//    @Bean
//    public GenericObjectPoolConfig redisPool() {
//        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
//        RedisProperties.Pool pool = redisProperties.getLettuce().getPool();
//        genericObjectPoolConfig.setMaxTotal(pool.getMaxActive());
//        genericObjectPoolConfig.setMaxIdle(pool.getMaxIdle());
//        genericObjectPoolConfig.setMinIdle(pool.getMinIdle());
//        genericObjectPoolConfig.setMaxWaitMillis(pool.getMaxWait().toMillis());
////        genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(1000);
//        return genericObjectPoolConfig;
//    }
//
//
//    @Bean
//    @Primary
////    @Scope(value = "prototype")
//    public RedisConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig poolConfig) {
//        LettucePoolingClientConfiguration lettucePoolingClientConfiguration = LettucePoolingClientConfiguration.builder()
//                .poolConfig(poolConfig)
//                .build();
//        //如果集群配置不为空，则配置redis集群模式
//        if (redisProperties.getCluster() != null) {
//            RedisProperties.Cluster clusterProperties = redisProperties.getCluster();
//            RedisClusterConfiguration config = new RedisClusterConfiguration(clusterProperties.getNodes());
//            if (clusterProperties.getMaxRedirects() != null) {
//                config.setMaxRedirects(clusterProperties.getMaxRedirects());
//            }
//
//            if (redisProperties.getPassword() != null) {
//                config.setPassword(RedisPassword.of(redisProperties.getPassword()));
//            }
//            return new LettuceConnectionFactory(config, lettucePoolingClientConfiguration);
//        } else {
//            //redis 单机模式
//            RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//            config.setHostName(redisProperties.getHost());
//            config.setPort(redisProperties.getPort());
//            config.setPassword(RedisPassword.of(redisProperties.getPassword()));
//            return new LettuceConnectionFactory(config, lettucePoolingClientConfiguration);
//        }
//
////        RedisClusterConfiguration redisConfig = new RedisClusterConfiguration();
////        Set<RedisNode> nodeses = new HashSet<>();
////        List<String> nodes = redisProperties.getCluster().getNodes();
////        for (String node : nodes) {
////            node = node.replaceAll("\\s", "").replaceAll("\n", "");
////            if (!"".equals(node)) {
////                String host = node.split(":")[0];
////                int port = Integer.valueOf(node.split(":")[1]);
////                nodeses.add(new RedisNode(host, port));
////            }
////        }
////        redisConfig.setClusterNodes(nodeses);
////        redisConfig.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
////        redisConfig.setPassword(redisProperties.getPassword());
////        return new LettuceConnectionFactory(redisConfig, lettucePoolingClientConfiguration);
//    }


//     @Bean(name = "redisTemplate01")
//     @ConditionalOnMissingBean(
//             name = {"redisTemplate"}
//     )
//     public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//         RedisSerializer protoStuffRedisSerializer = new GenericJackson2JsonRedisSerializer();
// //        ProtoStuffRedisSerializer protoStuffRedisSerializer = new ProtoStuffRedisSerializer();
// //        ProtostuffSerializer protoStuffRedisSerializer = new ProtostuffSerializer();
//         RedisSerializer stringRedisSerializer = new StringRedisSerializer();
//         RedisTemplate<String, Object> template = new RedisTemplate();
//         LettuceConnectionFactory factory = (LettuceConnectionFactory) redisConnectionFactory;
//         factory.setValidateConnection(true);
//         template.setConnectionFactory(factory);
//         template.setKeySerializer(stringRedisSerializer);
//         template.setValueSerializer(protoStuffRedisSerializer);
//         template.setHashKeySerializer(stringRedisSerializer);
//         template.setHashValueSerializer(protoStuffRedisSerializer);
//         template.afterPropertiesSet();
// //        redisConnectionFactory.setShareNativeConnection(false);
//         return template;
//     }

    // /**
    //  * 配置lettuce连接池
    //  *
    //  * @return
    //  */
    // @Bean
    // @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    // public GenericObjectPoolConfig redisPool() {
    //     return new GenericObjectPoolConfig<>();
    // }


}