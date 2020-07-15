package frameworkMy.BeanDefinitionReader;

import frameworkMy.Context.XmlReaderContext;
import org.springframework.beans.factory.BeanDefinitionStoreException;
import org.w3c.dom.Document;

public interface BeanDefinitionDocumentReader {
    void registerBeanDefinitions(Document doc, XmlReaderContext readerContext)
            throws BeanDefinitionStoreException;
}
