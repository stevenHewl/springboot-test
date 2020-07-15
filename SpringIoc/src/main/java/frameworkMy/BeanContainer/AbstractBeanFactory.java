package frameworkMy.BeanContainer;

import frameworkMy.Bean.BeanDefinition;
import org.apache.shiro.util.Assert;
import org.springframework.beans.*;
import org.springframework.beans.factory.*;
import org.springframework.lang.Nullable;

import java.lang.reflect.*;
import java.util.*;

/**
 * 一个抽象类， 实现了 bean 的方法，包含一个map，用于存储bean 的名字和bean的定义
 *
 * @author stateis0
 */
public abstract class AbstractBeanFactory implements BeanFactory {
    /**
     * 容器
     */
    private HashMap<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    // BEAN_MAP相当于一个Spring容器, 拥有应用所有bean的实例
    private static final Map<Class<?>, Object> BEAN_MAP = new HashMap<Class<?>, Object>();

    /** List of bean definition names, in registration order. */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    /**
     * 注册 bean定义 的抽象方法实现，这是一个模板方法， 调用子类方法doCreate，
     */
    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beandefinition) throws Exception {
        Assert.hasText(beanName, "frameworkMy.Bean name must not be empty");
        Assert.notNull(beandefinition, "BeanDefinition must not be null");
        beanDefinitionNames.add(beanName);
        beanDefinitionMap.put(beanName, beandefinition);
    }

    @Override
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }

    /**
     * 根据bean的名称获取bean， 如果没有，则抛出异常 如果有， 则从bean定义对象获取bean实例
     */
    @Override
    public <T> T getBean(String name) {
        BeanDefinition beandefinition = beanDefinitionMap.get(name);
        if (beandefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object beanInstance = beandefinition.getBean();
        Object bean;
        if (beanInstance == null) {
            doCreateBean(name, beandefinition);
            beanInstance = beandefinition.getBean();
        }
        bean = beanInstance;
        if (beanInstance != null) {
            bean = getObjectForBeanInstance(beanInstance, name);
        }
        return (T) bean;
    }

    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        // Now we have the bean instance, which may be a normal bean or a FactoryBean.
        // If it's a FactoryBean, we use it to create a bean instance, unless the
        // caller actually wants a reference to the factory.
        if (!(beanInstance instanceof ProxBeanFactory)) {
            return beanInstance;
        }
        Object object = null;
        try {
            // Return bean instance from factory.
            ProxBeanFactory factory = (ProxBeanFactory) beanInstance;
            object = factory.getObject(this);
        } catch (Exception es){
            es.printStackTrace();
        }
        return object;
    }

    public void preInstantiateSingletons() throws BeansException {
        // Trigger initialization of all non-lazy singleton beans...
        for (String beanName : beanDefinitionNames) {
            doCreateBean(beanName, beanDefinitionMap.get(beanName));
        }
    }

    protected void doCreateBean(final String beanName, final BeanDefinition mbd)
            throws BeanCreationException {
        // Instantiate the bean.
        Object beanInstance = instantiate(mbd, beanName);
        // Initialize the bean instance.
        try {
            // 该方法用于填充Bean
            populateBean(mbd, beanInstance);
        }
        catch (Throwable ex) {
        }
        mbd.setBean(beanInstance);
        BEAN_MAP.put(mbd.getClass(), beanInstance);
    }

    public Object instantiate(BeanDefinition bd, @Nullable String beanName) {
        Constructor<?> constructorToUse;
        final Class<?> clazz = bd.getBeanClass();
        if (clazz != null && !Modifier.isPublic(clazz.getModifiers())) {
            throw new BeanCreationException("frameworkMy.Bean class isn't public, and non-public access not allowed: " + clazz.getName());
        }
        if (clazz.isInterface()) {
            throw new BeanInstantiationException(clazz, "Specified class is an interface");
        }
        try {
            Class[] cl = bd.getClasses();
            constructorToUse = clazz.getDeclaredConstructor(cl);
        } catch (Throwable ex) {
            throw new BeanInstantiationException(clazz, "No default constructor found", ex);
        }
        return BeanUtils.instantiateClass(constructorToUse, bd.getConstructorArgumentValues());
    }

    protected void populateBean(BeanDefinition mbd, Object beanInstance) {
        List<PropertyValue>  pvs = mbd.getPropertyValueList();
        try {
            setPropertyValues(new MutablePropertyValues(pvs), beanInstance, mbd);
        }
        catch (BeansException ex) {
        }
    }

    public void setPropertyValues(PropertyValues pvs, Object bean, BeanDefinition mbd){
        List<PropertyValue> propertyValues = (pvs instanceof MutablePropertyValues ?
                ((MutablePropertyValues) pvs).getPropertyValueList() : Arrays.asList(pvs.getPropertyValues()));
        for (PropertyValue pv : propertyValues) {
            try {
                // 根据给定属性名称获取 给定的bean中的属性对象
                Field declaredField = bean.getClass().getDeclaredField(pv.getName());
                // 设置属性的访问权限
                declaredField.setAccessible(true);
                // 获取定义的属性中的对象
                Object value = pv.getValue();
                // 反射注入bean的属性
                declaredField.set(bean, value);
            } catch (Exception es) {
            }
        }
    }
}