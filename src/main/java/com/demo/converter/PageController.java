package com.demo.converter;

import com.demo.converter.entities.Currency;
import com.demo.converter.entities.Log;
import com.demo.converter.entities.ValCurs;
import com.demo.converter.utils.LogRepository;
import com.demo.converter.utils.Utilities;
import com.demo.converter.utils.ValCursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private Utilities utils;

    @Autowired
    private ValCursRepository repository;

    @Autowired
    private LogRepository logRepository;

    @GetMapping("/")
    public String getPage(ModelMap map){
        ValCurs valCurs = utils.getInfo();
        try {
            repository.save(valCurs);
        }
        catch (DataIntegrityViolationException d){
            System.out.println("data already exists");
        }

        ValCurs currValCurs = null;
        String[] dateSplit = LocalDate.now().toString().split("-");
        String date = String.format("%s.%s.%s", dateSplit[2], dateSplit[1],dateSplit[0]);
        currValCurs = repository.getByDate(date);

        List<String> logs = new ArrayList<>();
        for(Log log : logRepository.findAll()){
            logs.add(String.format("%s %.2f ---> %s %.2f  %s/%s/%s %s:%s", log.getFirstCharCode(),
                    log.getFirstValue(), log.getSecondCharCode(), log.getSecondValue(),
                    log.getTime().getDayOfMonth(), log.getTime().getMonth(), log.getTime().getYear(),
                    log.getTime().getHour(), log.getTime().getMinute()));
        }
        map.put("logs", logs);


        map.put("currencies", currValCurs.getCharCodes());

        return "page";
    }
    @PostMapping
    public String process(HttpServletRequest request, ModelMap map, Model model){

        ValCurs currValCurs = null;
        String[] dateSplit = LocalDate.now().toString().split("-");
        String date = String.format("%s.%s.%s", dateSplit[2], dateSplit[1],dateSplit[0]);
        currValCurs = repository.getByDate(date);

        String charcodeOne = request.getParameter("charcode-one");
        String charcodeTwo = request.getParameter("charcode-two");
        String input = request.getParameter("input_value");

        Currency currOne = currValCurs.getCurrency(charcodeOne);
        Currency currTwo = currValCurs.getCurrency(charcodeTwo);

        logRepository.save(new Log(currOne.getCharCode(), currTwo.getCharCode(), currOne.getName(),
                currTwo.getName(), Double.parseDouble(input), Double.parseDouble(input)));

        double output = currOne.getValue()/currOne.getNominal()*Double.parseDouble(input)/currTwo.getValue();
        DecimalFormat df = new DecimalFormat("0.00");

        model.addAttribute("output", df.format(output));
        model.addAttribute("input_value", input);
        model.addAttribute("charcode_one", currOne.getCharCode());
        model.addAttribute("charcode_two", currTwo.getCharCode());
        model.addAttribute("name_one", currOne.getName());
        model.addAttribute("name_two", currTwo.getName());
        model.addAttribute("nominal_one", currOne.getNominal());
        model.addAttribute("nominal_two", currTwo.getNominal());
        model.addAttribute("value_one", currOne.getValue());
        model.addAttribute("value_two", currTwo.getValue());


        List<String> logs = new ArrayList<>();
        for(Log log : logRepository.findAll()){
            logs.add(String.format("%s %.2f ---> %s %.2f  %s/%s/%s %s:%s", log.getFirstCharCode(),
                    log.getFirstValue(), log.getSecondCharCode(), log.getSecondValue(),
                    log.getTime().getDayOfMonth(), log.getTime().getMonth(), log.getTime().getYear(),
                    log.getTime().getHour(), log.getTime().getMinute()));
        }
        map.put("logs", logs);
        map.put("currencies", currValCurs.getCharCodes());

        return "page";
    }
}
