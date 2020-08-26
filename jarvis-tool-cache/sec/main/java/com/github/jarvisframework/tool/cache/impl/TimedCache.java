package com.github.jarvisframework.tool.cache.impl;

import com.github.jarvisframework.tool.cache.GlobalPruneTimerEnum;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时缓存<br>
 * 此缓存没有容量限制，对象只有在过期后才会被移除
 *
 * @param <K> 键类型
 * @param <V> 值类型
 * @author 王涛
 * @since 1.0, 2020-07-29 14:14:03
 */
public class TimedCache<K, V> extends AbstractCache<K, V> {

    private static final long serialVersionUID = 1L;

    /** 正在执行的定时任务 */
    private ScheduledFuture<?> pruneJobFuture;

    /**
     * 构造
     *
     * @param timeout 超时（过期）时长，单位毫秒
     */
    public TimedCache(long timeout) {
        this(timeout, new HashMap<>());
    }

    /**
     * 构造
     *
     * @param timeout 过期时长
     * @param map 存储缓存对象的map
     */
    public TimedCache(long timeout, Map<K, CacheObject<K, V>> map) {
        this.capacity = 0;
        this.timeout = timeout;
        this.cacheMap = map;
    }

    // ---------------------------------------------------------------- prune
    /**
     * 清理过期对象
     *
     * @return 清理数
     */
    @Override
    protected int pruneCache() {
        int count = 0;
        Iterator<CacheObject<K, V>> values = cacheMap.values().iterator();
        CacheObject<K, V> co;
        while (values.hasNext()) {
            co = values.next();
            if (co.isExpired()) {
                values.remove();
                onRemove(co.key, co.obj);
                count++;
            }
        }
        return count;
    }

    // ---------------------------------------------------------------- auto prune
    /**
     * 定时清理
     *
     * @param delay 间隔时长，单位毫秒
     */
    public void schedulePrune(long delay) {
        this.pruneJobFuture = GlobalPruneTimerEnum.INSTANCE.schedule(this::prune, delay);
    }

    /**
     * 取消定时清理
     */
    public void cancelPruneSchedule() {
        if (null != pruneJobFuture) {
            pruneJobFuture.cancel(true);
        }
    }

}
