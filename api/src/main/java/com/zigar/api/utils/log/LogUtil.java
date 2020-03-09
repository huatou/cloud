package com.zigar.api.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class LogUtil {

    private static String logPrefix = "ZIGAR --> ";
    private static LogUtil instance;
    private static Logger logger = null;
    private static Map<Class, Logger> loggerList = new HashMap<Class, Logger>();//用于缓存logger对象

    /**
     * 定义私有构造方法实现单例
     */
    private LogUtil() {
    }

    /**
     * 功能说明：获取服务实例的静态方法
     * 修改说明：
     *
     * @param obj 传入调用此方法的对象
     * @return
     * @author badao
     * @date
     */
    public synchronized static LogUtil getInst(Object obj) {
        if (instance == null) {
            instance = new LogUtil();
        }
        LogUtil.logger = loggerList.get(obj.getClass());
        if (LogUtil.logger == null) {
            LogUtil.logger = LoggerFactory.getLogger(obj.getClass());
            //LogUtil.logger = Logger.getLogger(obj.getClass());
            loggerList.put(obj.getClass(), LogUtil.logger);
        }
        return instance;
    }

    /**
     * 功能说明：获取服务实例的静态方法
     * 修改说明：
     *
     * @param clazz 传入调用此方法的类型
     * @return
     * @author badao
     * @date
     */
    public synchronized static LogUtil getInst(Class clazz) {
        if (instance == null) {
            instance = new LogUtil();
        }
        LogUtil.logger = loggerList.get(clazz);
        if (LogUtil.logger == null) {
            LogUtil.logger = LoggerFactory.getLogger(clazz);
            loggerList.put(clazz, LogUtil.logger);
        }
        return instance;
    }

    /**
     * 功能说明：获取服务实例的静态方法
     * 修改说明：
     *
     * @return
     * @author badao
     * @date
     */
    public synchronized static LogUtil getInst() {
        if (instance == null) {
            instance = new LogUtil();
        }
        LogUtil.logger = loggerList.get(LogUtil.class);
        if (LogUtil.logger == null) {
            LogUtil.logger = LoggerFactory.getLogger(LogUtil.class);
            loggerList.put(LogUtil.class, LogUtil.logger);
        }
        return instance;
    }

    public void trace(String message) {
        LogUtil.logger.trace(logPrefix + message);
    }

    public void trace(String message, Throwable t) {
        LogUtil.logger.trace(logPrefix + message, t);
    }

    public void debug(String message) {
        LogUtil.logger.debug(logPrefix + message);
    }

    public void debug(String message, Throwable t) {
        LogUtil.logger.debug(logPrefix + message, t);
    }

    public void info(String message) {
        LogUtil.logger.info(logPrefix + message);
    }

    public void info(String message, Throwable t) {
        LogUtil.logger.info(logPrefix + message, t);
    }

    public void warn(String message) {
        LogUtil.logger.warn(logPrefix + message);
    }

    public void warn(String message, Throwable t) {
        LogUtil.logger.warn(logPrefix + message, t);
    }

    public void error(String message, Object... arguments) {
        LogUtil.logger.error(logPrefix + message, arguments);
    }

    public void error(String message, Throwable t, Object... arguments) {
        LogUtil.logger.error(logPrefix + message, t, arguments);
    }
}
