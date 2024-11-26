package com.alek7ey;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InstagramScraper {

    // смотрит количество подписчиков в аккаунте alina_garakh
    public static void main(String[] args) {
        // Укажите путь к вашему WebDriver
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        // Создайте экземпляр WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Перейдите на страницу входа в Instagram
            driver.get("https://www.instagram.com/accounts/login/");

            // Подождите, пока страница загрузится
            Thread.sleep(5000); // Задержка в 5 секунд

            // Введите имя пользователя и пароль
            WebElement usernameField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            usernameField.sendKeys("alek7ey1248");
            passwordField.sendKeys("alek7instag");

            // Нажмите кнопку входа
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            // нажатие на кнопку loginButton
            loginButton.click();

            // Подождите, пока страница загрузится
            Thread.sleep(5000); // Задержка в 5 секунд

            // Перейдите на страницу пользователя alina_garakh
            driver.get("https://www.instagram.com/alina_garakh/");
            // Подождите, пока страница загрузится
            Thread.sleep(5000); // Задержка в 5 секунд

            // Подождите, пока элемент не станет видимым
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Найдите элемент, содержащий количество подписчиков
            WebElement followersElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button']/span[@title]")));
            // Получите текст из элемента
            String followers = followersElement.getAttribute("title");

            // Выведите количество подписчиков в консоль
            System.out.println("Количество подписчиков: " + followers);
        } catch (InterruptedException e) {
            // Обработка исключения, если произошла ошибка при ожидании
            e.printStackTrace();
        } finally {
            // Закройте браузер
            driver.quit();
        }
    }

//    // смотрит количество подписчиков в моем инстаграме
//    public static void main(String[] args) {
//        // Укажите путь к вашему WebDriver
//        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
//
//        // Создайте экземпляр WebDriver
//        WebDriver driver = new ChromeDriver();
//
//        try {
//            // Перейдите на страницу входа в Instagram
//            driver.get("https://www.instagram.com/accounts/login/");
//
//            // Подождите, пока страница загрузится
//            Thread.sleep(5000); // Задержка в 5 секунд
//
//            // Введите имя пользователя и пароль
//            WebElement usernameField = driver.findElement(By.name("username"));
//            WebElement passwordField = driver.findElement(By.name("password"));
//            usernameField.sendKeys("alek7ey1248");
//            passwordField.sendKeys("alek7instag");
//
//            // Нажмите кнопку входа
//            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
//            loginButton.click();
//
//            // Подождите, пока страница загрузится
//            Thread.sleep(5000); // Задержка в 5 секунд
//
//            // Перейдите на страницу пользователя
//            driver.get("https://www.instagram.com/alek7ey1248/");
//
//            // Подождите, пока страница загрузится
//            Thread.sleep(5000); // Задержка в 5 секунд
//
//            // Найдите элемент, содержащий количество подписчиков
//            WebElement followersElement = driver.findElement(By.xpath("//a[contains(@href, 'followers')]/span"));
//
//            // Получите текст из элемента
//            String followers = followersElement.getAttribute("title");
//
//            // Выведите количество подписчиков в консоль
//            System.out.println("Количество подписчиков: " + followers);
//        } catch (InterruptedException e) {
//            // Обработка исключения, если произошла ошибка при ожидании
//            e.printStackTrace();
//        } finally {
//            // Закройте браузер
//            driver.quit();
//        }
//    }
}