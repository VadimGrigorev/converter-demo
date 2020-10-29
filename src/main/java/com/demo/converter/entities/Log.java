package com.demo.converter.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime time;

    @Column
    private String firstCharCode;

    @Column
    private String secondCharCode;

    @Column
    private String firstName;

    @Column
    private String secondName;

    @Column
    private double firstValue;

    @Column
    private double secondValue;

    public Log() {
    }

    public Log(String firstCharCode, String secondCharCode, String firstName, String secondName, double firstValue, double secondValue) {
        this.firstCharCode = firstCharCode;
        this.secondCharCode = secondCharCode;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstValue = firstValue;
        this.secondValue = secondValue;
        this.time = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getFirstCharCode() {
        return firstCharCode;
    }

    public void setFirstCharCode(String firstCharCode) {
        this.firstCharCode = firstCharCode;
    }

    public String getSecondCharCode() {
        return secondCharCode;
    }

    public void setSecondCharCode(String secondCharCode) {
        this.secondCharCode = secondCharCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public double getFirstValue() {
        return firstValue;
    }

    public void setFirstValue(double firstValue) {
        this.firstValue = firstValue;
    }

    public double getSecondValue() {
        return secondValue;
    }

    public void setSecondValue(double secondValue) {
        this.secondValue = secondValue;
    }
}
