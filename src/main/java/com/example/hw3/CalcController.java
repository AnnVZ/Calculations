package com.example.hw3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

@Controller
public class CalcController {
    private static String pattern = "###,###.######";
    private static DecimalFormat decimalFormat = new DecimalFormat(pattern);

    @GetMapping("/")
    public String getIndex(Model model) {
        return "index";
    }

    @GetMapping("/sum")
    @ResponseBody
    public String getSum(@RequestParam(value = "number1", defaultValue = "0") String number1,
                         @RequestParam(value = "number2", defaultValue = "0") String number2,
                         Model model) {
        return setFormat((new BigDecimal(number1).add(new BigDecimal(number2))));
    }

    @GetMapping("/dif")
    @ResponseBody
    public String getDiff(@RequestParam(value = "number1", defaultValue = "0") String number1,
                         @RequestParam(value = "number2", defaultValue = "0") String number2,
                         Model model) {
        return setFormat((new BigDecimal(number1).subtract(new BigDecimal(number2))));
    }

    @GetMapping("/mult")
    @ResponseBody
    public String getMult(@RequestParam(value = "number1", defaultValue = "0") String number1,
                          @RequestParam(value = "number2", defaultValue = "0") String number2,
                          Model model) {
        return setFormat((new BigDecimal(number1).multiply(new BigDecimal(number2))));
    }

    @GetMapping("/div")
    @ResponseBody
    public String getDiv(@RequestParam(value = "number1", defaultValue = "0") String number1,
                          @RequestParam(value = "number2", defaultValue = "0") String number2,
                          Model model) {
        return setFormat((new BigDecimal(number1).divide(new BigDecimal(number2), 6, RoundingMode.HALF_UP)));
    }

    private String setFormat(BigDecimal number) {
        return decimalFormat.format(number.setScale(6, RoundingMode.HALF_UP));
    }
}