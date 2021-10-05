package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.RegistrationConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import helpers.Attach;

import static java.lang.String.format;
import static java.lang.System.*;
import static java.lang.System.getProperty;


public class TestBase {
    public static RegistrationConfig registration =
            ConfigFactory.create(RegistrationConfig.class);
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;

        String name = registration.name();
        String key = registration.key();
        String url = getProperty("REMOTE_URL");

        Configuration.remote = format("https://%s:%s@%s", name, key, url);

}

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}