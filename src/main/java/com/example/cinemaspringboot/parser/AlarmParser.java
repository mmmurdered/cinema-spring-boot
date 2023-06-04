package com.example.cinemaspringboot.parser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class AlarmParser {

    public static List<String> findRegionsWhereAlarm() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\murdered\\Desktop\\untitled2\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origins=*");

        WebDriver driver = new ChromeDriver(options);

        List<String> regions = new ArrayList<>();
        try {

            driver.get("https://alarmmap.online/");

            Thread.sleep(5000);

            WebElement[] elements = driver.findElements(By.className("amo-map-alarms-list-item-name")).toArray(new WebElement[0]);
            System.out.println(elements.length);
            for (WebElement element : elements) {
                regions.add(element.getAttribute("innerHTML"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
        return regions;
    }
}
