package com.nanjolono.payment.utils;
/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-17 21:12
 */
@Component
@Slf4j
public class RedisUtils {

    @Autowired
    private ValueOperations<String, String> valueOperations;


    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24L;
    /**  过期时长，2H */
    public final static long EXPIRE_TWO_HOUR = 60 * 60 * 2L;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;


    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        if(valueOperations.setIfAbsent(key, value)) {
            return true;
        }
        //currentValue=A   这两个线程的value都是B  其中一个线程拿到锁
        String currentValue = valueOperations.get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValue = valueOperations.getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = valueOperations.get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                valueOperations.getOperations().delete(key);
            }
        }catch (Exception e) {
            log.error("【redis分布式锁】解锁异常, {}", e);
        }
    }


    public void set(String key, Object value, long expire){
        String strdata=toJson(value);
        valueOperations.set(key, strdata);
        if(expire != NOT_EXPIRE){
            valueOperations.getOperations().expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void  set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            valueOperations.getOperations().expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            valueOperations.getOperations().expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return valueOperations.get(key);
        //return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        valueOperations.getOperations().delete(key);
    }

    /**
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return JSON.toJSONString(object, SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return JSON.parseObject(json, clazz);
    }


    /**
     * 清除机构缓存数据
     */
    public void clearOrgCache(){
        Set<String> keys= valueOperations.getOperations().keys("org*");
        for(String key:keys){
            this.delete(key);
        }
    }

    /**
     * 清除巡检机构【巡检经理】缓存数据
     * */
    public void clearInspectOrg(){
        String key = "pcOrgList:inspectOrgs";
        this.delete(key);
    }
    /**
     * 清除经营类目缓存数据
     */
    public void clearBusiness(){
        Set<String> keys= valueOperations.getOperations().keys("businessScope*");
        for(String key:keys){
            this.delete(key);
        }
    }
    /**
     * 清除shiro权限缓存数据(机构)
     * */
    public void clearPopedomListByOrgId(String orgId){
        String key = "popedomList:"+orgId+"&*";
        this.delete(key);
    }
    /**
     * 清除shiro权限缓存数据(角色)
     * */
    public void clearPopedomListByRoleId(String roleId){
        String key = "popedomList:*&"+roleId;
        this.delete(key);
    }
}
