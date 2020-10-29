package com.demo.converter.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @JsonProperty("ID")
    private String cid;

    @Column(name="numcode")
    @JsonProperty("NumCode")
    private int numCode;

    @Column(name="charcode")
    @JsonProperty("CharCode")
    private String charCode;

    @Column
    @JsonProperty("Nominal")
    private int nominal;

    @Column
    @JsonProperty("Name")
    private String name;

    @Column
    @JsonProperty("Value")
    private double value;

    public Currency() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getNumCode() {
        return numCode;
    }

    public void setNumCode(int numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = Double.parseDouble(value.replace(",","."));
    }

}
