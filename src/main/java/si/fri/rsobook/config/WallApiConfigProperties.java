package si.fri.rsobook.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("properties")
public class WallApiConfigProperties {

    @ConfigValue(watch = true)
    private Integer maxWallPosts;

    public Integer getMaxWallPosts() {
        return maxWallPosts;
    }

    public void setMaxWallPosts(Integer maxWallPosts) {
        this.maxWallPosts = maxWallPosts;
    }
}
