package ro.x13.asig.db.config;

import org.springframework.stereotype.Component;
import ro.x13.asig.security.UrlConfig;

@Component
public class UrlConfigImpl implements UrlConfig {

    @Override
    public String getStartUrl() {
        return "/index.xhtml";
    }
}
