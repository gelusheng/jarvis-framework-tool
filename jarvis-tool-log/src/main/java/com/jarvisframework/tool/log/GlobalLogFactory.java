package com.jarvisframework.tool.log;

/**
 * 全局日志工厂类<br>
 * 用于减少日志工厂创建，减少日志库探测
 *
 * @author 王涛
 * @since 1.0, 2020-08-03 16:43:32
 */
public class GlobalLogFactory {

    private static volatile LogFactory currentLogFactory;

    private static final Object lock = new Object();

    /**
     * 获取单例日志工厂类，如果不存在创建之
     *
     * @return 当前使用的日志工厂
     */
    public static LogFactory get() {
        if (null == currentLogFactory) {
            synchronized (lock) {
                if (null == currentLogFactory) {
                    currentLogFactory = LogFactory.create();
                }
            }
        }
        return currentLogFactory;
    }

    /**
     * 自定义日志实现
     *
     * @param logFactoryClass 日志工厂类
     * @return 自定义的日志工厂类
     * @see cn.hutool.log.dialect.slf4j.Slf4jLogFactory
     * @see cn.hutool.log.dialect.log4j.Log4jLogFactory
     * @see cn.hutool.log.dialect.log4j2.Log4j2LogFactory
     * @see cn.hutool.log.dialect.commons.ApacheCommonsLogFactory
     * @see cn.hutool.log.dialect.jdk.JdkLogFactory
     * @see cn.hutool.log.dialect.console.ConsoleLogFactory
     */
    public static LogFactory set(Class<? extends LogFactory> logFactoryClass) {
        try {
            return set(logFactoryClass.newInstance());
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not instance LogFactory class!", e);
        }
    }

    /**
     * 自定义日志实现
     *
     * @param logFactory 日志工厂类对象
     * @return 自定义的日志工厂类
     * @see cn.hutool.log.dialect.slf4j.Slf4jLogFactory
     * @see cn.hutool.log.dialect.log4j.Log4jLogFactory
     * @see cn.hutool.log.dialect.log4j2.Log4j2LogFactory
     * @see cn.hutool.log.dialect.commons.ApacheCommonsLogFactory
     * @see cn.hutool.log.dialect.jdk.JdkLogFactory
     * @see cn.hutool.log.dialect.console.ConsoleLogFactory
     */
    public static LogFactory set(LogFactory logFactory) {
        logFactory.getLog(GlobalLogFactory.class).debug("Custom Use [{}] Logger.", logFactory.name);
        currentLogFactory = logFactory;
        return currentLogFactory;
    }
}
