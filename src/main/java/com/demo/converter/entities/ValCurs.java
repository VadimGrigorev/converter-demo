package com.demo.converter.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ValCurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @Column(unique = true)
    @JsonProperty("Date")
    private String date;

    @Column
    @JsonProperty("name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JacksonXmlElementWrapper(useWrapping = false)
    @JsonProperty("Valute")
    private List<Currency> currencies = new ArrayList<>();

    public ValCurs() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }

    public List<String> getCharCodes(){
        List<String> charCodes = new ArrayList<>();
        currencies.forEach(s -> charCodes.add(s.getCharCode()));
        return charCodes;
    }

    public Currency getCurrency(String charcode){
        Currency toReturn = new Currency();
        for(Currency c : currencies){
            if(c.getCharCode().equals(charcode)) toReturn = c;
        }
        return toReturn;
    }
}
