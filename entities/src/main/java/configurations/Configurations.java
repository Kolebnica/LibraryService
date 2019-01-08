package configurations;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("skiprope-configs")
public class Configurations {

    @ConfigValue("health.etcd-url")
    private String etcdHealthUrl;

    @ConfigValue(value = "tolerance-test", watch = true)
    private Integer toleranceTest = 100;

    public String getEtcdHealthUrl() {
        return etcdHealthUrl;
    }

    public void setEtcdHealthUrl(String etcdHealthUrl) {
        this.etcdHealthUrl = etcdHealthUrl;
    }

    public Integer getToleranceTest() {
        return toleranceTest;
    }

    public void setToleranceTest(Integer toleranceTest) {
        this.toleranceTest = toleranceTest;
    }
}
