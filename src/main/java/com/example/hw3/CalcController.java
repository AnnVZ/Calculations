package com.example.hw3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getSum(@RequestParam(value = "number1", defaultValue = "0") String number1,
                                 @RequestParam(value = "number2", defaultValue = "0") String number2,
                                 Model model) {
        return new ResponseEntity<>(setFormat((new BigDecimal(number1).add(new BigDecimal(number2)))), HttpStatus.OK);
    }

    @GetMapping("/dif")
    @ResponseBody
    public ResponseEntity getDiff(@RequestParam(value = "number1", defaultValue = "0") String number1,
                         @RequestParam(value = "number2", defaultValue = "0") String number2,
                         Model model) {
        return new ResponseEntity<>(setFormat((new BigDecimal(number1).subtract(new BigDecimal(number2)))), HttpStatus.OK);
    }

    @GetMapping("/mult")
    @ResponseBody
    public ResponseEntity getMult(@RequestParam(value = "number1", defaultValue = "0") String number1,
                          @RequestParam(value = "number2", defaultValue = "0") String number2,
                          Model model) {
        return new ResponseEntity<>(setFormat((new BigDecimal(number1).multiply(new BigDecimal(number2)))), HttpStatus.OK);
    }

    @GetMapping("/div")
    @ResponseBody
    public ResponseEntity getDiv(@RequestParam(value = "number1", defaultValue = "0") String number1,
                          @RequestParam(value = "number2", defaultValue = "0") String number2,
                          Model model) {
        if (new BigDecimal(number2).stripTrailingZeros().equals(new BigDecimal(0))) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(setFormat((new BigDecimal(number1).divide(new BigDecimal(number2), 6, RoundingMode.HALF_UP))), HttpStatus.OK);
    }

    @GetMapping("/spaceformat")
    @ResponseBody
    public ResponseEntity getDiv(@RequestParam(value = "number", defaultValue = "0") String number,
                                 Model model) {
        String formattedNumber = decimalFormat.format(new BigDecimal(number).setScale(6, RoundingMode.HALF_UP)).replaceAll(",", " ");
        return new ResponseEntity<>(formattedNumber, HttpStatus.OK);
    }

    @GetMapping("/mathround")
    @ResponseBody
    public ResponseEntity mathRound(@RequestParam(value = "number", defaultValue = "0") String number,
                                 Model model) {
        return new ResponseEntity<>(new BigDecimal(number).setScale(0, RoundingMode.HALF_UP), HttpStatus.OK);
    }

    @GetMapping("/bankround")
    @ResponseBody
    public ResponseEntity bankRound(@RequestParam(value = "number", defaultValue = "0") String number,
                                 Model model) {
        return new ResponseEntity<>(new BigDecimal(number).setScale(0, RoundingMode.HALF_EVEN), HttpStatus.OK);
    }

    @GetMapping("/truncround")
    @ResponseBody
    public ResponseEntity truncRound(@RequestParam(value = "number", defaultValue = "0") String number,
                                 Model model) {
        return new ResponseEntity<>(new BigDecimal(number).setScale(0, RoundingMode.DOWN), HttpStatus.OK);
    }

    private String setFormat(BigDecimal number) {
        return number.setScale(6, RoundingMode.HALF_UP).stripTrailingZeros().toString();
    }
}