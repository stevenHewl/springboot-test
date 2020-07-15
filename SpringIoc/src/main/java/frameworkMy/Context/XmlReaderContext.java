package frameworkMy.Context;

import frameworkMy.BeanContainer.BeanFactory;
import frameworkMy.BeanDefinitionReader.XmlBeanDefinitionReader;
import org.springframework.beans.factory.parsing.ReaderContext;
import org.springframework.beans.factory.xml.NamespaceHandlerResolver;
import org.springframework.core.io.Resource;

public class XmlReaderContext extends ReaderContext {

    XmlBeanDefinitionReader reader;
    private final NamespaceHandlerResolver namespaceHandlerResolver;

    public XmlReaderContext(Resource resource, XmlBeanDefinitionReader reader, NamespaceHandlerResolver namespaceHandlerResolver) {
        super(resource, null, null, null);
        this.reader = reader;
        this.namespaceHandlerResolver = namespaceHandlerResolver;
    }

    public final BeanFactory getRegistry() {
        return this.reader.getRegistry();
    }
}
