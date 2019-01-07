package configurations;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("skiprope-configs")
public class Configurations {

    @ConfigValue("health.etcd-url")
    private String etcdHealthUrl;

    public String getEtcdHealthUrl() {
        return etcdHealthUrl;
    }

    public void setEtcdHealthUrl(String etcdHealthUrl) {
        this.etcdHealthUrl = etcdHealthUrl;
    }
}
