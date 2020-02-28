package top.yzlin.chainpharmacymanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import top.yzlin.chainpharmacymanagementsystem.dao.MedicineDAO;
import top.yzlin.chainpharmacymanagementsystem.entity.Medicine;

@Configuration
public class MainConfig {

    @Bean
    public RepositoryRestConfigurer repositoryRestConfigurer() {
        return new RepositoryRestConfigurer() {
            @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
                config.exposeIdsFor(Medicine.class);

            }
        };
    }

    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        return p -> p.setOneIndexedParameters(true);
    }
}
