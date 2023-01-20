package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URL;
import java.util.EnumSet;
import java.util.logging.Level;


class DriverFactory {
    private static final String FIREFOX = "firefox";
    private static final String IE = "ie";
    private static final String DEFAULT = "chrome";
    private static final String REMOTE = "remote";
    private static WebDriver driver;

    public static BrowserMobProxy myProxy;

    public static int proxyPort = 4446;

    static WebDriver getDriver() throws IOException {
        myProxy = new BrowserMobProxyServer();
        myProxy.start(proxyPort);
        Proxy seleniumProxy = ClientUtil.createSeleniumProxy(myProxy);
        String hostIp = Inet4Address.getLocalHost().getHostAddress();
        seleniumProxy.setHttpProxy(hostIp + ":" + myProxy.getPort());
        seleniumProxy.setSslProxy(hostIp + ":" + myProxy.getPort());
        myProxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);


        DesiredCapabilities seleniumCapabilities = new DesiredCapabilities();
        seleniumCapabilities.setCapability(CapabilityType.PROXY, seleniumProxy);
        seleniumCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        String browser = System.getenv("BROWSER");
        if (browser == null) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
        switch (browser) {
            case "IE":
                WebDriverManager.iedriver().setup();
                return new InternetExplorerDriver();

            case "EDGE":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();

            case "REMOTE":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    chromeOptions.addArguments("--headless");
                }
                return new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), chromeOptions);

            default:
                ChromeOptions chromeOption = new ChromeOptions();
                LoggingPreferences logPrefs = new LoggingPreferences();
                chromeOption.addArguments("--no-sandbox");
                chromeOption.addArguments("--disable-notifications");
                chromeOption.addArguments("start-maximized");
                logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
                chromeOption.setCapability("goog:loggingPrefs", logPrefs);
                chromeOption.addArguments("--disable-web-security");
                chromeOption.addArguments("--allow-insecure-localhost");
                chromeOption.addArguments("--ignore-urlfetcher-cert-requests");
                if ("Y".equalsIgnoreCase(System.getenv("HEADLESS"))) {
                    chromeOption.addArguments("--headless");
                }
                EnumSet<CaptureType> captureTypes = CaptureType.getAllContentCaptureTypes();
                captureTypes.addAll(CaptureType.getCookieCaptureTypes());
                captureTypes.addAll(CaptureType.getHeaderCaptureTypes());
                captureTypes.addAll(CaptureType.getRequestCaptureTypes());
                captureTypes.addAll(CaptureType.getResponseCaptureTypes());

                myProxy.setHarCaptureTypes(captureTypes);
                myProxy.newHar("MyHAR");

                WebDriverManager.chromedriver().setup();
                return new ChromeDriver(seleniumCapabilities);
        }
    }

    public static void tearDownProxy(String specName, String scenarioName) throws IOException {
        Har har = myProxy.getHar();
        File myHarFile = new File(System.getProperty("user.dir") + "/harFolder/" + specName + " " + scenarioName + ".har");
        har.writeTo(myHarFile);
        myProxy.stop();
    }
}

