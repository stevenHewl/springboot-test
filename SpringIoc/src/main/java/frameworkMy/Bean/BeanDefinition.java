package frameworkMy.Bean;

import org.springframework.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/*
* BeanDefinition 是Bean的基本数据结构
* 包含 需要一个 frameworkMy.Bean 对象，一个Class对象，一个ClassName字符串，还需要一个元素集合 PropertyValues。这些就能组成一个最基本的 BeanDefinition 类了
* 还有是否延迟加载，依赖关系等
* */
public class BeanDefinition {
    /**
     * bean
     */
    private Object bean;

    /**
     * bean 的 CLass 对象
     */
    private Class beanClass;

    /**
     * bean 的类全限定名称
     */
    private String ClassName;

    /**
     * 类的属性集合
     */
    private List<PropertyValue> propertyValueList = new ArrayList<>();

    // 构造函数参数值 集合
    private List<Object> argumentValueList = new ArrayList<>();

    // 构造函数参数类型 集合
    private List<Class> classList = new ArrayList<>();

    /**
     * 获取bean对象
     */
    public Object getBean() {
        return this.bean;
    }

    /**
     * 设置bean的对象
     */
    public void setBean(Object bean) {
        this.bean = bean;
    }

    /**
     * 获取bean的Class对象
     */
    public Class getBeanClass() {
        return this.beanClass;
    }

    /**
     * 通过设置类名称反射生成Class对象
     */
    public void setClassname(String name) {
        this.ClassName = name;
        try {
            this.beanClass = Class.forName(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取bean的属性集合
     */
    public List<PropertyValue> getPropertyValueList() {
        return this.propertyValueList;
    }

    /**
     * 设置bean的属性
     */
    public void setPropertyValues(List<PropertyValue> pvList) {
        this.propertyValueList = pvList;
    }

    public void setConstructorArgumentValue(Object value, Class typeClass){
        this.argumentValueList.add(value);
        classList.add(typeClass);
    }

    public Object[] getConstructorArgumentValues(){
        return this.argumentValueList.toArray(new Object[this.argumentValueList.size()]);
    }

    public Class[] getClasses(){
        return this.classList.toArray(new Class[this.classList.size()]);
    }
}
