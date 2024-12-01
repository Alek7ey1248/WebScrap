package com.alek7ey.insta_scrap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InstagramScraper {

    // путь к вашему WebDriver
    private static final String PATH_TO_WEB_DRIVER = "/usr/local/bin/chromedriver";

    // страница входа в Instagram
    private static final String INSTAGRAM_LOGIN_PAGE = "https://www.instagram.com/accounts/login/";

    // имя пользователя
    private static final String USERNAME = "alek7ey1248";

    // пароль
    private static final String PASSWORD = "alek7instag";

    // страницы на которые заходим для получения информации
    private Account[] accounts;
    public Account[] getAccounts() {
        return accounts;
    }


    // WebDriver - это интерфейс, который предоставляет методы для взаимодействия с веб-страницами.
    WebDriver driver;

    // конструктор
    public InstagramScraper(Account[] accounts) {

        this.accounts = accounts;

        System.setProperty("webdriver.chrome.driver", PATH_TO_WEB_DRIVER);

        this.driver = new ChromeDriver();

        loginToPage(); // вход на исходную страницу Instagram
    }

    /*
    * Основной метод
    * - заходит на стартовую страницу,
    * потом на страницу пользователя.
    * Смотрит всю необходимую информацию о пользователе
    */
    public void getAccountsInfo() {
        try {
            for (Account account : accounts) {
                // переход на страницу пользователя о котором нужно получить информацию
                goToPage(account);
                // -------------------------------------------------

                getFoloowres(account); // смотрит количество подписчиков в аккаунте
                getPublications(account); // смотрит количество публикаций в аккаунте
                getFoloowing(account); // смотрит количество подписок в аккаунте

            }
            // -------------------------------------------------
        } finally {
            // Закройте браузер
            driver.quit();
        }
    }


    /*
     * Метод вспомагательный
     * - вход на страницу Instagram с которой
     * будем заходить на другие страницы
     */
    private void loginToPage() {
        try {
            // Перейдите на страницу входа в Instagram
            driver.get(INSTAGRAM_LOGIN_PAGE);

            // Подождите, пока страница загрузится
            Thread.sleep(3000); // Задержка в 3 секунд

            // Введите имя пользователя и пароль
            WebElement usernameField = driver.findElement(By.name("username"));
            WebElement passwordField = driver.findElement(By.name("password"));
            usernameField.sendKeys(USERNAME);
            passwordField.sendKeys(PASSWORD);

            // Нажмите кнопку входа
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));

            // нажатие на кнопку loginButton
            loginButton.click();

            // Подождите, пока страница загрузится
            Thread.sleep(3000); // Задержка в секунд
        } catch (InterruptedException e) {
            // Обработка исключения, если произошла ошибка при ожидании
            e.printStackTrace();
        }
    }


    /*
     * Метод вспомагательный
     * - переход на страницу пользователя о котором нужно получить информацию
     */
    private void goToPage(Account account) {
        String pageUrl = "https://www.instagram.com/" + account.getLogin() + "/";
        try {
            // Перейдите на страницу пользователя
            driver.get(pageUrl);

            // Подождите, пока страница загрузится
            Thread.sleep(3000); // Задержка в 5 секунд
        } catch (InterruptedException e) {
            // Обработка исключения, если произошла ошибка при ожидании
            e.printStackTrace();
        }
    }


    /*
     * Метод вспомагательный
     * - смотрит количество публикаций в аккаунте
     */
    private void getPublications(Account account) {
        // создает экземпляр класса WebDriverWait, который используется для явного ожидания определенных условий в течение заданного времени. В данном случае, driver - это объект WebDriver, а Duration.ofSeconds(10) указывает, что максимальное время ожидания составляет 10 секунд. Это означает, что WebDriver будет ждать до 10 секунд, пока не выполнится указанное условие (например, элемент станет видимым на странице).
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String publications;
        try {
            // Найдите элемент, содержащий количество публикаций
            WebElement publicationsElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='html-span xdj266r x11i5rnm xat24cr x1mh8g0r xexx8yu x4uap5 x18d9i69 xkhd6sd x1hl2dhg x16tdsg8 x1vvkbs']")));
            // Получите текст из элемента
            publications = publicationsElement.getText();
        } catch (Exception e) {
            // Обработка случая, когда элемент не найден
            publications = "\u001B[31mНе удалось получить\u001B[0m";
            System.err.println("Не удалось получить количество публикаций для аккаунта: " + account.getLogin());
        }

        account.setNumPublications(publications);
    }



    /*
     * Метод вспомагательный
     * - смотрит количество подписчиков в аккаунте
     */
    private void getFoloowres(Account account) {
                // создает экземпляр класса WebDriverWait, который используется для явного ожидания определенных условий в течение заданного времени. В данном случае, driver - это объект WebDriver, а Duration.ofSeconds(10) указывает, что максимальное время ожидания составляет 10 секунд. Это означает, что WebDriver будет ждать до 10 секунд, пока не выполнится указанное условие (например, элемент станет видимым на странице).
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

                String followers;
                // элемент, содержащий количество подписчиков
                WebElement followersElement = null;
                // два варианта найти количество подписчиков
                try {
                    // Попробуйте первый XPath локатор (это для закрытых аккаунтов скорее всего)
                    followersElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@type='button']/span[@title]")));
                    followers = followersElement.getAttribute("title");
                } catch (Exception e) {
                    try {
                        // Если первый локатор не сработал, попробуйте второй
                        followersElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/followers')]/span/span")));
                        followers = followersElement.getText();
                    } catch (Exception ex) {
                        // Обработка случая, когда оба локатора не сработали
                        followers = "\u001B[31mНе удалось получить\u001B[0m";
                    }
                }

                // устанавливаем количество подписчиков в аккаунте
                account.setNumFollowers(followers);
    }

    /*
     * Метод вспомагательный
     * - смотрит количество подписок в аккаунте
     */
    private void getFoloowing(Account account) {
        // создает экземпляр класса WebDriverWait, который используется для явного ожидания определенных условий в течение заданного времени. В данном случае, driver - это объект WebDriver, а Duration.ofSeconds(10) указывает, что максимальное время ожидания составляет 10 секунд. Это означает, что WebDriver будет ждать до 10 секунд, пока не выполнится указанное условие (например, элемент станет видимым на странице).
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        String foloowing;
        WebElement foloowingElement = null;
        try {
            // Попробуйте первый XPath локатор (для закрытых аккаунтов)
            foloowingElement = driver.findElement(By.xpath("//li[contains(@class, 'x6s0dn4') and .//span[contains(text(), 'подписок')]]//span[@class='html-span xdj266r x11i5rnm xat24cr x1mh8g0r xexx8yu x4uap5 x18d9i69 xkhd6sd x1hl2dhg x16tdsg8 x1vvkbs']"));
            foloowing = foloowingElement.getAttribute("title");
        } catch (Exception e) {
            try {
                // Если первый локатор не сработал, попробуйте второй
                foloowingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@href, '/following')]/span")));
                foloowing = foloowingElement.getText();
            } catch (Exception ex) {
                // Обработка случая, когда оба локатора не сработали
                foloowing = "\u001B[31mНе удалось получить\u001B[0m";
                System.err.println("Не удалось получить количество подписок для аккаунта: " + account.getLogin());
            }
        }

        // Установите количество подписок в аккаунте
        account.setNumFoloowing(foloowing);
    }






    public static void main(String[] args) {
        String login1 = "alina_garakh";
//        String login2 = "chub.style";
//        String login3 = "mamay_fight_club";
//        String login4 = "starodubi";

        Account account1 = new Account(login1);
//        Account account2 = new Account(login2);
//        Account account3 = new Account(login3);
//        Account account4 = new Account(login4);

        Account[] accounts = {account1};

        InstagramScraper instagramScraper = new InstagramScraper(accounts);
        instagramScraper.getAccountsInfo();
        // выводим информацию о пользователях
        for (int i = 0; i < instagramScraper.getAccounts().length; i++) {
            System.out.println(instagramScraper.getAccounts()[i].toString());
        }

    }
}