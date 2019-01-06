package com.grosup.ttzy.util;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {

    private static Logger log = Logger.getLogger(SpringUtil.class);
    
    /**
     * 当前IOC
     */
    private static ApplicationContext applicationContext;

    /*
     * @param arg0
     * 
     * @throws BeansException
     * 
     * @see
     * org.springframework.context.ApplicationContextAware#setApplicationContext
     * (org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        log.info("====================arg0:"+arg0);
        applicationContext = arg0;
    }
    
    public static <T>T getBean(Class<T> type){        
        return  applicationContext.getBean(type);
    }
}