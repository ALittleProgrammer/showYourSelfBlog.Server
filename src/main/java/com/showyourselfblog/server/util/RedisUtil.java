package com.showyourselfblog.server.util;

import com.showyourselfblog.server.config.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @Description Redis工具类
 * @program mycloud
 * @Author Peng Jiankun
 * @Date 2020-05-13 19:20
 **/

@Service
public class RedisUtil {

    @Resource
    RedisConfig redisConfig;

    Logger logger= LoggerFactory.getLogger(this.getClass());

    JedisPool pool=null;

    boolean flag=false;

    @Bean
    private JedisPool getPool(){
        logger.info("do getPool");
        if (redisConfig==null){
            logger.error("redisConfig is null");
        }
        pool=redisConfig.jedisPoolFactory();
        if (pool==null){
            logger.error("获取pool连接池错误！");
            flag=false;
        }else {
            logger.info("get pool");
            flag=true;
        }
        return pool;
    }

    public boolean isValid(String token){
        Jedis jedis = null;
        logger.info("redisUtil flag is "+this.flag);
        if (!flag){
            pool=getPool();
        }
        if (pool!=null){
            try {
                jedis = pool.getResource();
                if (jedis.exists(token)) {
                    logger.info(token + " is Valid! ");
                    return true;
                } else {
                    logger.warn(token + " is not Valid! ");
                    return false;
                }
            }catch (RuntimeException e){
                logger.error("catch RuntimeException");
                logger.error(e.toString());
                if (jedis!=null){
                    pool.returnBrokenResource(jedis);
                }
                return false;
            }finally {
                logger.error("finally");
                if (jedis!=null){
                    pool.returnResource(jedis);
                }
            }
        }else {
            logger.error("连接池获取错误！！！");
            return false;
        }
    }

    private void action(final String token, final String host, final Long life, final String NX){
        if (!flag){
            pool=getPool();
        }
        Jedis jedis=null;
        if (pool!=null){
            try {
                 jedis= pool.getResource();
                logger.info(jedis.set(token, host, NX, "PX", life));
            }catch (RuntimeException e){
                logger.error(e.toString());
                if (jedis!=null){
                    pool.returnBrokenResource(jedis);
                }
            }finally {
                if (jedis!=null){
                    pool.returnResource(jedis);
                }
            }
        }else {
            logger.error("连接池获取错误！！！");
        }
    }

    public void add(final String token, final String host, final Long life){
        if (isValid(token)) {
            action(token,host,life,"XX");
        }else {
            action(token, host, life, "NX");
        }
    }

    public String get(final String token){
        if (!flag){
            pool=getPool();
        }else {
            Jedis jedis=null;
            try {
                jedis=pool.getResource();
                return jedis.get(token);
            }catch (RuntimeException e){
                pool.returnResource(jedis);
            }finally {
                pool.returnBrokenResource(jedis);
            }
        }
        return null;
    }

    public void remove(final String token){
        if (!flag){
            pool=getPool();
        }
        if (pool!=null){
            Jedis jedis = null;
            try {
                jedis = pool.getResource();
                jedis.del(token);
            }catch (RuntimeException e){
                logger.error("catch RuntimeException");
                logger.error(e.toString());
                if (jedis!=null){
                    pool.returnBrokenResource(jedis);
                }
            }finally {
                logger.error("finally");
                if (jedis!=null){
                    pool.returnResource(jedis);
                }
            }
        }else {
            logger.error("连接池获取错误！！！");
        }
    }
}
