package ru.croc.task17;

public class Order {
    private int ID;
    private String uLogin;
    private String article;

    public Order(int ID, String uLogin, String article){
        this.ID = ID;
        this.uLogin = uLogin;
        this.article = article;
    }

    public int getID(){
        return ID;
    }

    public String getuLogin(){
        return uLogin;
    }

    public String getArticle(){
        return article;
    }
}