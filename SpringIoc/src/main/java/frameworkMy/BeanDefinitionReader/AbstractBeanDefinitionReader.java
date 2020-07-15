package frameworkMy.BeanDefinitionReader;

import frameworkMy.BeanContainer.BeanFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    @Nullable
    private ResourceLoader resourceLoader;

    private BeanFactory registry;


    protected AbstractBeanDefinitionReader(BeanFactory registry) {
        Assert.notNull(registry, "BeanDefinitionRegistry must not be null");
        this.registry = registry;
        this.resourceLoader = new PathMatchingResourcePatternResolver();
    }

    public final BeanFactory getRegistry() {
        return this.registry;
    }

    @Nullable
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
}
