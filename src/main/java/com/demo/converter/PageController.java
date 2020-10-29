package com.demo.converter;

import com.demo.converter.entities.Currency;
import com.demo.converter.entities.ValCurs;
import com.demo.converter.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;

@Controller
public class PageController {

    @Autowired
    private Utilities utils;

    @GetMapping("/")
    public String getPage(ModelMap map){
        ValCurs currValCurs = utils.getCurrentValCurs();

        map.put("logs", utils.getConversionLogs());
        map.put("currencies", currValCurs.getCharCodes());

        return "page";
    }
    @PostMapping
    public String process(HttpServletRequest request, ModelMap map, Model model){

        //get input values
        String charcodeOne = request.getParameter("charcode-one");
        String charcodeTwo = request.getParameter("charcode-two");
        double input = Double.parseDouble(request.getParameter("input_value"));

        //get valCurs from db
        ValCurs currValCurs = utils.getCurrentValCurs();
        Currency currOne = currValCurs.getCurrency(charcodeOne);
        Currency currTwo = currValCurs.getCurrency(charcodeTwo);

        //convert and log conversion
        double output = currOne.getValue()/currOne.getNominal()*input/currTwo.getValue();
        DecimalFormat df = new DecimalFormat("0.00");
        utils.logConversion(currOne, currTwo, input, output);

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

        map.put("logs", utils.getConversionLogs());
        map.put("currencies", currValCurs.getCharCodes());

        return "page";
    }
}
