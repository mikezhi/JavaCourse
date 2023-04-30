package ru.croc.task17;

public class Order {
    private int ID;
    private String uLogin;
    private String arcicle;

    public Order(int ID, String uLogin, String arcicle){
        this.ID = ID;
        this.uLogin = uLogin;
        this.arcicle = arcicle;
    }

    public int getID(){
        return ID;
    }

    public String getuLogin(){
        return getuLogin();
    }

    public String getArcicle(){
        return getArcicle();
    }
}