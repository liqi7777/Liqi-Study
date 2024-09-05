package com.jz.test.redistest.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 取得Spring的上下文（也就是Factory,使通过Factory可以得到目标JavaBean）
 * 
 * @author Ban
 *
 */
@Component
public class SpringContext implements ApplicationContextAware {

	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		synchronized (this) {
			if (SpringContext.applicationContext == null) {
				SpringContext.applicationContext = applicationContext;
			}
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> c) {

		return applicationContext.getBean(c);
	}

}
