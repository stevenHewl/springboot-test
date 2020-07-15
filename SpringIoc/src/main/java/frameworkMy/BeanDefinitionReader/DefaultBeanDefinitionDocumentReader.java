package frameworkMy.BeanDefinitionReader;

import frameworkMy.Bean.BeanDefinition;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import frameworkMy.Context.XmlReaderContext;

import org.springframework.lang.Nullable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class DefaultBeanDefinitionDocumentReader implements BeanDefinitionDocumentReader {

    @Nullable
    private XmlReaderContext readerContext;

    @Override
    public void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
            throws BeanDefinitionStoreException{
        this.readerContext = readerContext;
        doRegisterBeanDefinitions(doc.getDocumentElement());
    }

    protected void doRegisterBeanDefinitions(Element root) {
        // 解析元素的根节点及根节点下的所有子节点并添加进注册容器
        parseBeanDefinitions(root);
    }

    /**
     * 解析元素的根节点及根节点下的所有子节点并添加进注册容器
     *
     * @param root XML 文件根节点
     */
    private void parseBeanDefinitions(Element root) {
        // 读取根元素的所有子元素
        NodeList nl = root.getChildNodes();
        // 遍历子元素
        for (int i = 0; i < nl.getLength(); i++) {
            // 获取根元素的给定位置的节点
            Node node = nl.item(i);
            if (node instanceof Element) {
                // 强转为父类型元素
                Element ele = (Element) node;
                // 解析给给定的节点，包括name，class，property， name， value，ref
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 解析给给定的节点，包括name，class，property， name， value，ref
     * @param ele XML 解析元素
     */
    private void processBeanDefinition(Element ele) {
        // 获取给定元素的 name 属性
        String beanName = ele.getAttribute("name");
        // 获取给定元素的 class 属性
        String className = ele.getAttribute("class");
        // 创建一个bean定义对象
        BeanDefinition beanDefinition = new BeanDefinition();
        // 设置bean 定义对象的 全限定类名
        beanDefinition.setClassname(className);

        // 向 bean 注入配置文件中的成员变量
        addPropertyValues(ele, beanDefinition);

        addConstructorArgument(ele, beanDefinition);

        // 向注册容器 添加bean名称和bean定义
        try {
            readerContext.getRegistry().registerBeanDefinition(beanName, beanDefinition);
        } catch (Exception es){
            es.printStackTrace();
        }
    }

    /**
     * 添加配置文件中的属性元素到bean定义实例中
     *
     * @param ele 元素
     * @param beandefinition bean定义 对象
     */
    private void addPropertyValues(Element ele, BeanDefinition beandefinition) {
        // 获取给定元素的 property 属性集合
        NodeList propertyNode = ele.getElementsByTagName("property");
        // 循环集合
        for (int i = 0; i < propertyNode.getLength(); i++) {
            // 获取集合中某个给定位置的节点
            Node node = propertyNode.item(i);
            // 类型判断
            if (node instanceof Element) {
                // 将节点向下强转为子元素
                Element propertyEle = (Element) node;
                // 元素对象获取 name 属性
                String name = propertyEle.getAttribute("name");
                if (name.equals("interceptorNames")){
                    NodeList listNode =  propertyEle.getElementsByTagName("list");
                    if (listNode != null && listNode.getLength() > 0) {
                        NodeList valueNode = ((Element)listNode.item(0)).getElementsByTagName("value");
                        String[] interceptorNames = new String[valueNode.getLength()];
                        for (int j = 0; j < valueNode.getLength(); j++) {
                            Node node2 = valueNode.item(j);
                            interceptorNames[j] = node2.getFirstChild().getNodeValue();
                        }
                        beandefinition.getPropertyValueList().add(new PropertyValue(name, interceptorNames));
                    }
                    continue;
                }
                // 元素对象获取 value 属性值
                String value = propertyEle.getAttribute("value");
                // 判断value不为空
                if (value != null && value.length() > 0) {
                    // 向给定的 “bean定义” 实例中添加该成员变量
                    beandefinition.getPropertyValueList().add(new PropertyValue(name, value));
                }
            }
        }
    }

    private void addConstructorArgument(Element ele, BeanDefinition beandefinition) {
        // 获取给定元素的 property 属性集合
        NodeList propertyNode = ele.getElementsByTagName("constructor-arg");
        // 循环集合
        for (int i = 0; i < propertyNode.getLength(); i++) {
            // 获取集合中某个给定位置的节点
            Node node = propertyNode.item(i);
            // 类型判断
            if (node instanceof Element) {
                // 将节点向下强转为子元素
                Element propertyEle = (Element) node;
                // 元素对象获取 value 属性值
                String value = propertyEle.getAttribute("value");
                // 判断value不为空
                if (value != null && value.length() > 0) {
                    // 向给定的 “bean定义” 实例中添加该成员变量
                    beandefinition.setConstructorArgumentValue(value, String.class);
                }
            }
        }
    }
}
