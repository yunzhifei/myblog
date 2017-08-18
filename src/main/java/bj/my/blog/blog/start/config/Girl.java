package bj.my.blog.blog.start.config;

import lombok.Data;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "s")
@Data
@Log
public class Girl {
    int pro;
    int suffix;
}
