package com.demo.converter.utils;

import com.demo.converter.entities.Currency;
import com.demo.converter.entities.Log;
import com.demo.converter.entities.ValCurs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class Utilities {

    @Autowired
    private ValCursRepository repository;

    @Autowired
    private LogRepository logRepository;

    //get and save info for current day to db
    public ValCurs saveInfo(){
        ValCurs valCurs = null;
        try {
            String[] todaySplit = LocalDate.now().toString().split("-");
            String currentDate = String.format("%s/%s/%s", todaySplit[2], todaySplit[1], todaySplit[0]);

            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req="+currentDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ObjectMapper mapper = new XmlMapper();
            InputStream is = conn.getInputStream();
            valCurs = mapper.readValue(is, ValCurs.class);
            try {
                repository.save(valCurs);
            }
            catch (DataIntegrityViolationException d){
                System.out.println("data already exists");
            }

        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        return valCurs;
    }

    //get valCurs for current date from db
    public ValCurs getCurrentValCurs(){
        String[] dateSplit = LocalDate.now().toString().split("-");
        String date = String.format("%s.%s.%s", dateSplit[2], dateSplit[1],dateSplit[0]);
        ValCurs valCurs = repository.getByDate(date);
        if(valCurs == null){
            saveInfo();
            valCurs = repository.getByDate(date);
        }
        return valCurs;
    }


    //save a log of conversion to db
    public void logConversion(Currency currOne, Currency currTwo, double input, double output){
        logRepository.save(new Log(currOne.getCharCode(), currTwo.getCharCode(), input, output));
    }

    public List<String> getConversionLogs(){
        List<String> logs = new ArrayList<>();
        for(Log log : logRepository.findAll()){
            logs.add(log.toString());
        }
        Collections.reverse(logs);
        return logs;
    }

}
