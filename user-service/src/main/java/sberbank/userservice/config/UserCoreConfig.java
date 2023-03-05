package sberbank.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(sberbank.coreservicecommon.config.CoreCommonConfig.class)
public class UserCoreConfig {
}
