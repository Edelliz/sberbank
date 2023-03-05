package sberbank.coreservicecommon.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigurationPackage(basePackages = "sberbank.coreservicecommon")
@ComponentScan({"sberbank.coreservicecommon"})
@RequiredArgsConstructor
public class CoreCommonConfig {
}
