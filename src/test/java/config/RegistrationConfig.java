package config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/registration.properties"})
public interface RegistrationConfig extends Config {

    String name();
    String key();

}
