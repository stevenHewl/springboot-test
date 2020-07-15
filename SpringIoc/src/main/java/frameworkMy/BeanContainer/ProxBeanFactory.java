package frameworkMy.BeanContainer;

import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.*;
import org.springframework.aop.framework.adapter.AdvisorAdapterRegistry;
import org.springframework.aop.framework.adapter.GlobalAdvisorAdapterRegistry;
import org.springframework.aop.target.SingletonTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBeanNotInitializedException;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

public class ProxBeanFactory extends ProxyCreatorSupport {

    @Nullable
    private String[] interceptorNames;

    @Nullable
    private String targetName;

    @Nullable
    private transient BeanFactory beanFactory;

    private AdvisorAdapterRegistry advisorAdapterRegistry = GlobalAdvisorAdapterRegistry.getInstance();

    /**
     * Whether the advisor chain has already been initialized.
     */
    private boolean advisorChainInitialized = false;

    /**
     * If this is a singleton, the cached singleton proxy instance.
     */
    @Nullable
    private Object singletonInstance;


    @Nullable
    private transient ClassLoader proxyClassLoader = ClassUtils.getDefaultClassLoader();

    public Object getObject(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
        initializeAdvisorChain(); // 为代理对象配置Advisor链
        return getSingletonInstance();
    }

    private synchronized void initializeAdvisorChain() throws AopConfigException, BeansException {
        if (this.advisorChainInitialized) {
            return;
        }
        if (!ObjectUtils.isEmpty(this.interceptorNames)) {
            // Materialize interceptor chain from bean names.
            for (String name : this.interceptorNames) {
                // If we get here, we need to add a named interceptor.
                // We must check if it's a singleton or prototype.
                // Add the real Advisor/Advice to the chain.
                Object advice = this.beanFactory.getBean(name);
                addAdvisorOnChainCreation(advice, name);
            }
        }
        this.advisorChainInitialized = true;
    }

    private void addAdvisorOnChainCreation(Object next, String name) {
        // We need to convert to an Advisor if necessary so that our source reference
        // matches what we find from superclass interceptors.
        Advisor advisor = this.advisorAdapterRegistry.wrap(next);
        addAdvisor(advisor);
    }

    private synchronized Object getSingletonInstance() {
        if (this.singletonInstance == null) {
            this.setTargetSource(freshTargetSource());
            if (getProxiedInterfaces().length == 0 && !isProxyTargetClass()) {
                // Rely on AOP infrastructure to tell us what interfaces to proxy.
                Class<?> targetClass = getTargetClass();
                if (targetClass == null) {
                    throw new FactoryBeanNotInitializedException("Cannot determine target class for proxy");
                }
                setInterfaces(ClassUtils.getAllInterfacesForClass(targetClass, this.proxyClassLoader));
            }
            this.singletonInstance = getProxy(createAopProxy());
        }
        return this.singletonInstance;
    }

    private TargetSource freshTargetSource() {
        Object target = this.beanFactory.getBean(this.targetName);
        if (target instanceof TargetSource) {
            return (TargetSource) target;
        } else {
            return new SingletonTargetSource(target);
        }
    }

    protected Object getProxy(AopProxy aopProxy) {
        return aopProxy.getProxy(this.proxyClassLoader);
    }

}
