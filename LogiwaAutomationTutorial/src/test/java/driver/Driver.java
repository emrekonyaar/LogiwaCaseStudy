package driver;

import com.thoughtworks.gauge.*;
import net.lightbody.bmp.core.har.Har;
import org.openqa.selenium.WebDriver;
import util.SlackIntegration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

public class Driver {
    public static WebDriver webDriver;

    public SlackIntegration slackIntegration;

    @BeforeSpec
    public void initializeDriver() throws IOException {
        webDriver = DriverFactory.getDriver();

        webDriver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
    }

    @AfterScenario
    public void afterScenario(ExecutionContext context) throws IOException {
        DriverFactory.tearDownProxy(context.getCurrentSpecification().getName(),context.getCurrentScenario().getName());
    }

    @AfterSpec
    public void closeDriver() {
        webDriver.close();
        if(System.getenv("SLACK_INTEGRATION").equals(false)){
            slackIntegration.sendMessageToSlack("Emre");
        }
    }

    @AfterSuite
    public void quitDriver() {
        webDriver.quit();
    }
}
