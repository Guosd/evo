package com.github.framework.evo.base.autoconfiguration;

import com.github.framework.evo.base.MybatisConfig;
import com.github.framework.evo.base.paginator.OffsetLimitInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnClass({MybatisConfig.class})
@Import({MybatisConfig.class})
public class MybatisAutoConfiguration {
    public MybatisAutoConfiguration() {
    }

    @Bean
    @ConditionalOnMissingBean
    public OffsetLimitInterceptor offsetLimitInterceptor() {
        return new OffsetLimitInterceptor();
    }

}
