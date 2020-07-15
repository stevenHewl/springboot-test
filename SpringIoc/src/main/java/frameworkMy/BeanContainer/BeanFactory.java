package frameworkMy.BeanContainer;

import frameworkMy.Bean.BeanDefinition;
import org.springframework.beans.BeansException;

/*
* beanFactory是 Bean容器，容器至少需要两个简单的方法，一个获取Bean，一个注册Bean
* */
public interface BeanFactory {
    /**
     * 根据bean的名称从容器中获取bean对象
     *
     * @param name bean 名称
     * @return bean实例
     * @throws Exception 异常
     */
    <T> T getBean(String name);

    /**
     * 将bean注册到容器中
     *
     * @param name bean 名称
     * @param bean bean实例
     * @throws Exception 异常
     */
    void registerBeanDefinition(String name, BeanDefinition bean) throws Exception;

    int getBeanDefinitionCount();

    void preInstantiateSingletons() throws BeansException;
}
