import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class Uploadtest {
    String username = System.getenv("LT_USERNAME");
    String accesskey = System.getenv("LT_ACCESS_KEY");
    RemoteWebDriver driver;
    String gridURL = "@hub.lambdatest.com/wd/hub";
    String status = "";

    float ClickCommandTime;
    long CommandStart;
    long CommandStop;
    float SendKeyCommand;
    long SendKeysStart;
    long SendKeysStop;

    @BeforeMethod
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})
    public void setup(String browser, String version, String platform) throws Exception {

        browser = System.getProperty("browser") != null ? System.getProperty("browser") : browser;
        version = System.getProperty("version") != null ? System.getProperty("version") : version;
        platform = System.getProperty("platform") != null ? System.getProperty("platform") : platform;

        String path = System.getenv("default_directory");
        System.out.println(" system property: " + path);
        String jobid = System.getenv("JOB_ID");
        System.out.println(" jobid: " + jobid);
        HashMap<String, String> custom = new HashMap<>();
        custom.put("Abcd", "1234");
        StopWatch buildSTart = new StopWatch();
        buildSTart.start();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", browser);
        capabilities.setCapability("browserVersion", version);
        capabilities.setCapability("platformName", System.getenv("HYPEREXECUTE_PLATFORM"));
//        capabilities.setCapability("build", "TestNG Framework" + jobid);
        capabilities.setCapability("build", System.getProperty("build"));
        capabilities.setCapability("name", "testName");
        capabilities.setCapability("network", false);
        capabilities.setCapability("visual", true);
        capabilities.setCapability("video", true);
        //capabilities.setCapability("selenium_version", "4.1.2");
        capabilities.setCapability("console", true);
        //capabilities.setCapability("customData", custom);
        Random rand = new Random();
        // Generating random integers in range 0 to 9
        int int1 = rand.nextInt(10);
        // Printing random integer
        System.out.println(" generatedString: " + int1);
        File object = new File(".//Files//generatedString-" + int1 + ".txt");
        object.createNewFile();

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(".//Files//generatedString-" + int1 + ".txt")))) {
            writer.write(String.valueOf(capabilities));
            writer.newLine();  // method provided by BufferedWriter
        } catch (IOException e) {
        }

        try {

            System.out.println("++++++++++++++++++++++++" + capabilities + "=====================");
            String url = "http://" + username + ":" + accesskey + gridURL;
            System.out.println(url);
            StopWatch driverStart = new StopWatch();
            driverStart.start();
            driver = new RemoteWebDriver(new URL(url), capabilities);
            driverStart.stop();
            float timeElapsed = driverStart.getTime() / 1000f;
            System.out.println("Driver initiate time" + "   " + timeElapsed);

        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception f) {
            System.out.println(f.getMessage());
        }

    }

    @Test
    public void test1() {
        try {
            // driver.get("http://localhost:8000");
            //     for (int i = 0; i <= 5; i++) {

            driver.get("https://qa.boomi.com");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++" + System.getenv("GROUP_NUMBER") + "======================================");
            driver.get("https://self-signed.badssl.com/");
//            Thread.sleep(5000);
//            Demo sampleTest = new Demo();
//            sampleTest.sampleTest(driver);
            //Thread.sleep(10000000);
//            API test = new API();
//            test.aws();
            StopWatch UrlLoad = new StopWatch();
            UrlLoad.start();

            UrlLoad.stop();

            float timeElapsed = UrlLoad.getTime() / 1000f;
            System.out.println("Time took to load web app" + "   " + timeElapsed);

            System.out.println(driver.getTitle());

//            }

            status = "passed";
        } catch (Exception t) {
            System.out.println(t);
            status = "failed";
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + "passed");
            driver.quit();
        }
    }
}
