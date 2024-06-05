package com.org.oycm.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author ouyangcm
 * create 2024/6/5 16:03
 */
@Component
public class BeanUtils implements ApplicationContextAware {

    private static final Log log = LogFactory.getLog(BeanUtils.class);
    private static ApplicationContext applicationContext = null;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (BeanUtils.applicationContext == null) {
            BeanUtils.applicationContext = applicationContext;
            log.info("applicationContext set complete");
        }
    }

    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
