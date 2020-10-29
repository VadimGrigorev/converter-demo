package com.demo.converter.utils;

import com.demo.converter.entities.ValCurs;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;

@Component
public class Utilities {

    //get info for current day
    public ValCurs getInfo(){
        ValCurs valCurs = null;
        try {
            String[] todaySplit = LocalDate.now().toString().split("-");
            String currentDate = String.format("%s/%s/%s", todaySplit[2], todaySplit[1], todaySplit[0]);

            URL url = new URL("http://www.cbr.ru/scripts/XML_daily.asp?date_req="+currentDate);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            ObjectMapper mapper = new XmlMapper();
            InputStream is = conn.getInputStream();
            valCurs = mapper.readValue(is, ValCurs.class);

        }
        catch(IOException exc){
            exc.printStackTrace();
        }
        return valCurs;
    }
}
