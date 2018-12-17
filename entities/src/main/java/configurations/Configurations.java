package configurations;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("skiprope-configs")
public class Configurations {

    @ConfigValue("health.consul-url")
    private String consulHealthUrl;

    public String getConsulHealthUrl() {
        return consulHealthUrl;
    }

    public void setConsulHealthUrl(String consulHealthUrl) {
        this.consulHealthUrl = consulHealthUrl;
    }
}
