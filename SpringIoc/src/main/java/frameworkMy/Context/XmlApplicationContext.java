package frameworkMy.Context;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

public class XmlApplicationContext extends AbstractXmlApplicationContext {

    public XmlApplicationContext(@Nullable String... locations){
        if (locations != null) {
            Assert.noNullElements(locations, "Config locations must not be null");
            this.configLocations = new String[locations.length];
            for (int i = 0; i < locations.length; i++) {
                this.configLocations[i] = resolvePath(locations[i]).trim();
            }
        }
        else {
            this.configLocations = null;
        }
        refresh();
    }
}