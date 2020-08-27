package com.github.jarvisframework.tool.core.date;

/**
 * <p>日期时间单位，每个单位都是以毫秒为基数</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-24 16:18:01
 */
public enum DateUnitEnum {
    /** 一毫秒 */
    MS(1),
    /** 一秒的毫秒数 */
    SECOND(1000),
    /**一分钟的毫秒数 */
    MINUTE(SECOND.getMillis() * 60),
    /**一小时的毫秒数 */
    HOUR(MINUTE.getMillis() * 60),
    /**一天的毫秒数 */
    DAY(HOUR.getMillis() * 24),
    /**一周的毫秒数 */
    WEEK(DAY.getMillis() * 7);

    private final long millis;

    DateUnitEnum(long millis){
        this.millis = millis;
    }

    /**
     * @return 单位对应的毫秒数
     */
    public long getMillis(){
        return this.millis;
    }
}
