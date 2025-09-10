package com.bonc.dpi.cache;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bonc.dpi.config.Constant;

/**
 *  系统缓存Service
 *
 */
@Service
public class CacheService {
	
	protected final Logger logger = LoggerFactory.getLogger(CacheService.class);
	
	public static final long COMMON_TIMEOUTSECOND = 15552000l;
	
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    public String obtainKey(String ... perElement) throws Exception {
    	if(perElement == null || perElement.length == 0) {
    		throw new Exception("获取缓存key失败！【原因：参数为空】");
    	}
    	StringBuilder builder = new StringBuilder();
    	for(String element : perElement) {
    		builder.append(element);
    		builder.append(Constant.CACHE_KEY_SEG);
    	}
    	String returnKey = builder.toString();
    	return returnKey.substring(0, returnKey.length() - 1);
    }
    
    /**
     * 获取缓存
     * @param key
     * @return
     */
	@SuppressWarnings("unchecked")
	public <T> T getStrCache(String key) {
    	return (T) this.redisTemplate.opsForValue().get(key);
    }
    
    /**
     * 设置缓存
     * @param key
     * @param value
     * @param timeOutSecond 过期时间（单位：秒）
     * @return
     */
    public <T> boolean setStrCacheWithExpireTime(String key, T value, long timeOutSecond) {
    	try {
			this.redisTemplate.opsForValue().set(key, value, timeOutSecond, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 获取缓存
     * @param tableKey
     * @param conditionKey
     * @return
     */
    @SuppressWarnings("unchecked")
	public <T> T getHashCache(String tableKey, String conditionKey) {
    	return (T) this.redisTemplate.opsForHash().get(tableKey, conditionKey);
    }
    
    /**
     * 获取hash所有缓存数据
     * @param tableKey
     * @return map<obj, obj>
     */
    @SuppressWarnings("unchecked")
	public <T> T getAllHashCache(String tableKey) {
    	return (T) this.redisTemplate.opsForHash().entries(tableKey);
    }
    
    /**
     * 设置缓存（带过期时间）
     * @param tableKey
     * @param conditionKey
     * @param value
     * @param timeOutSecond 过期时间（单位：秒）
     * @return
     */
    public <T> boolean setHashCacheWithExpireTime(String tableKey, String conditionKey, T value, long timeOutSecond) {
    	try {
			this.redisTemplate.opsForHash().put(tableKey, conditionKey, value);
			expire(tableKey, timeOutSecond);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 设置缓存
     * @param tableKey
     * @param conditionKey
     * @param value
     * @return
     */
    public <T> boolean setHashCache(String tableKey, String conditionKey, T value) {
    	try {
			return setHashCacheWithExpireTime(tableKey, conditionKey, value, 0);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 删除缓存
     * @param tableKey
     * @param conditionKey
     * @return
     */
    public boolean delHashCache(String tableKey, String conditionKey) {
    	try {
			this.redisTemplate.opsForHash().delete(tableKey, conditionKey);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
    /**
     * 判断是否有缓存
     * @param tableKey
     * @param conditionKey
     * @return
     */
    public boolean hasHashCache(String tableKey, String conditionKey) {
    	try {
			return this.redisTemplate.opsForHash().hasKey(tableKey, conditionKey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    }
    
	/**
	 * 根据key获取过期时间
	 * 
	 * @param key 键 不能为null
	 * @return 时间(秒) 返回0代表为永久有效
	 */
	public long getExpire(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
    
	public boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 判断key是否存在
	 * 
	 * @param key 键
	 * @return true 存在 false不存在
	 */
	public boolean hasKey(String key) {
		try {
			return redisTemplate.hasKey(key);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除缓存
	 * 
	 * @param key 可以传一个值 或多个
	 */
	@SuppressWarnings("unchecked")
	public void delKey(String... key) {
		if (key != null && key.length > 0) {
			if (key.length == 1) {
				redisTemplate.delete(key[0]);
			} else {
				redisTemplate.delete(CollectionUtils.arrayToList(key));
			}
		}
	}
	
	/**
	 * 根据pattern匹配key
	 * 注：如果key过多，或是集群redis，不建议使用
	 * @param pattern
	 * @return
	 */
	public Set<String> keys(String pattern) {
		Set<String> keySet = null;
		try {
			keySet = redisTemplate.keys(pattern);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keySet;
	}
	
}
