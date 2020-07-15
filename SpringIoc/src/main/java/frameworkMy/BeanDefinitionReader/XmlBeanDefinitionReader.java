package frameworkMy.BeanDefinitionReader;

import frameworkMy.BeanContainer.BeanFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import frameworkMy.Context.XmlReaderContext;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.Set;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader  {

    private Class<? extends BeanDefinitionDocumentReader> documentReaderClass =
            DefaultBeanDefinitionDocumentReader.class;

    private final ThreadLocal<Set<EncodedResource>> resourcesCurrentlyBeingLoaded =
            new NamedThreadLocal<>("XML bean definition resources currently being loaded");

    /**
     * 构造器，必须包含一个beanFactory
     */
    public XmlBeanDefinitionReader(BeanFactory beanFactory) {
        super(beanFactory);
    }

    public int loadBeanDefinitions(String... locations) {
        Assert.notNull(locations, "Location array must not be null");
        int count = 0;
        try {
            for (String location : locations) {
                loadBeanDefinitions(location);
                count++;
            }
        }catch (Exception es){
            es.printStackTrace();
        }
        return count;
    }

    private void loadBeanDefinitions(String location) throws Exception {

        ResourceLoader resourceLoader = getResourceLoader();
        // 从资源加载器中获取输入流
        Resource resource = resourceLoader.getResource(location);

        InputStream inputstream = this.getClass().getResourceAsStream(location);
        String url = this.getClass().getResource(location).getFile();
        // 获取文档建造者工厂实例
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 工厂创建文档建造者
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        // 文档建造者解析流 返回文档对象
        Document doc = docBuilder.parse(url);

        // 根据给定的文档对象进行解析，并注册到bean容器中
        registerBeanDefinitions(doc, resource);

        // 关闭流
        inputstream.close();
    }

    public int registerBeanDefinitions(Document doc, Resource resource) throws BeanDefinitionStoreException {
        BeanDefinitionDocumentReader documentReader = createBeanDefinitionDocumentReader();
        int countBefore = getRegistry().getBeanDefinitionCount();
        XmlReaderContext readerContext = new XmlReaderContext(resource, this,null);
        documentReader.registerBeanDefinitions(doc, readerContext);
        return getRegistry().getBeanDefinitionCount() - countBefore;
    }

    protected BeanDefinitionDocumentReader createBeanDefinitionDocumentReader() {
        return BeanUtils.instantiateClass(this.documentReaderClass);
    }
}
