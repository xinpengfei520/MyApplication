package com.atguigu.l08_app_endcallsms;

/**
 * Created by xinpengfei on 2016/9/19.
 */
public class BlackNumber {

    private int id;
    private String name;
    private String number;

    public BlackNumber(int id, String number) {
        this.id = id;
        this.number = number;
    }

    public BlackNumber() {
    }

    public BlackNumber(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BlackNumber{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
