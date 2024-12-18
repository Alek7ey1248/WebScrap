package com.alek7ey.insta_scrap;

public class Account {

    private String login; // логин
    private String name; // имя
    private String numPublications; // количество публикаций
    private String numFollowers; // количество подписчиков
    private String numFoloowing; // количество подписок

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumPublications() {
        return numPublications;
    }

    public void setNumPublications(String numPublications) {
        this.numPublications = numPublications;
    }

    public String getNumFollowers() {
        return numFollowers;
    }

    public void setNumFollowers(String numFollowers) {
        this.numFollowers = numFollowers;
    }

    public String getNumFoloowing() {
        return numFoloowing;
    }

    public void setNumFoloowing(String numFoloowing) {
        this.numFoloowing = numFoloowing;
    }

    // конструктор
    public Account(String login) {
        this.login = login;
        this.name = " пока нет информации ";
        this.numPublications = " пока нет информации ";
        this.numFollowers = " пока нет информации ";
        this.numFoloowing = " пока нет информации ";
    }

    // переопределение метода toString
    @Override
    public String toString() {
        return "Account ----------------- \n" +
                "login='" + login + '\'' + ",\n" +
                "name='" + name + '\'' + ",\n" +
                "numPublications='" + numPublications + '\'' + ",\n" +
                "numFollowers='" + numFollowers + '\'' + ",\n" +
                "numFoloowing='" + numFoloowing + '\'' + "\n" +
                "--------------------------";
    }

}
