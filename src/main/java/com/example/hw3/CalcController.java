package com.example.hw3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class CalcController {

    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @GetMapping("/sum")
    @ResponseBody
    public String getSum(@RequestParam(value = "number1", defaultValue = "0") String number1,
                         @RequestParam(value = "number2", defaultValue = "0") String number2,
                         Model model) {
        return (new BigDecimal(number1).add(new BigDecimal(number2))).toString();
    }

    @GetMapping("/diff")
    @ResponseBody
    public String getDiff(@RequestParam(value = "number1", defaultValue = "0") String number1,
                         @RequestParam(value = "number2", defaultValue = "0") String number2,
                         Model model) {
        return (new BigDecimal(number1).subtract(new BigDecimal(number2))).toString();
    }
}