package tn.rostom.pi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);  // Adjust this depending on the size of the body
        filter.setIncludeHeaders(true);     // Logs headers as well
        filter.setAfterMessagePrefix("REQUEST DATA: ");
        return filter;
    }
}
