package frameworkMy.Context;

import frameworkMy.BeanContainer.AutowireBeanFactory;
import frameworkMy.BeanContainer.BeanFactory;
import frameworkMy.BeanDefinitionReader.XmlBeanDefinitionReader;
import org.springframework.beans.BeansException;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.lang.Nullable;

public abstract class AbstractXmlApplicationContext {

    @Nullable
    protected String[] configLocations;

    @Nullable
    private ConfigurableEnvironment environment;

    /** frameworkMy.Bean factory for this context. */
    @Nullable
    private static BeanFactory beanFactory;

    protected String resolvePath(String path) {
        return getEnvironment().resolveRequiredPlaceholders(path);
    }

    private ConfigurableEnvironment getEnvironment() {
        if (this.environment == null) {
            this.environment = new StandardEnvironment();
        }
        return this.environment;
    }

    /** Synchronization monitor for the "refresh" and "destroy". */
    private final Object startupShutdownMonitor = new Object();

    public void refresh() throws BeansException, IllegalStateException {
        synchronized (this.startupShutdownMonitor) {
            // internal bean factory.
            BeanFactory beanFactory = new AutowireBeanFactory();
            this.beanFactory = beanFactory;
            try {
                // 创建了Bean工厂，并创建 BeanDefinitions 放进Map里，以beanName为key
                loadBeanDefinitions(beanFactory);

                // Instantiate all remaining (non-lazy-init) singletons. 实例化所有的（non-lazy-init）单件
                finishBeanFactoryInitialization(beanFactory);
            }
            catch (BeansException ex) {
                // Propagate exception to caller.
                throw ex;
            }
        }
    }

    private void loadBeanDefinitions(BeanFactory beanFactory) {
        // Create a new XmlBeanDefinitionReader for the given BeanFactory.
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] configLocations = getConfigLocations();
        try {
            int count = beanDefinitionReader.loadBeanDefinitions(configLocations);
        }catch (Exception es){
            es.printStackTrace();
        }
    }

    @Nullable
    private String[] getConfigLocations() {
        return (this.configLocations != null ? this.configLocations : null);
    }

    /**
     * Finish the initialization of this context's bean factory,
     * initializing all remaining singleton beans.
     */
    protected void finishBeanFactoryInitialization(BeanFactory beanFactory) {
        // Instantiate all remaining (non-lazy-init) singletons.
        beanFactory.preInstantiateSingletons();
    }

    public Object getBean(String beanName){
        try {
            return this.beanFactory.getBean(beanName);
        } catch (Exception es){
            es.printStackTrace();
        }
        return null;
    }
}
