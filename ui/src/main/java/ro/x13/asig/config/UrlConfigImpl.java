package ro.x13.asig.config;

import org.springframework.stereotype.Component;
import ro.x13.asig.security.UrlConfig;

@Component
public class UrlConfigImpl implements UrlConfig {

    @Override
    public String getStartUrl() {
        return "/role";
    }
}
