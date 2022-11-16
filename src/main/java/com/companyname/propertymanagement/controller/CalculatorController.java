package com.companyname.propertymanagement.controller;

import com.companyname.propertymanagement.dto.CalculatorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/calculator")
public class CalculatorController
{
    @GetMapping("/add")
    public Double add(@RequestParam("num111") Double num1,@RequestParam("num222") Double num2)
    {
        return num1+num2;
    }

    @GetMapping("/sub/{num111}/{num222}")
    public Double sub(@PathVariable("num111") Double num1,@PathVariable("num222") Double num2)
    {
       Double result=null;
       if(num1>num2)
       {
           result=num1-num2;
       }
       else
       {
           result=num2-num1;
       }
        return result;
    }

    @PostMapping("/mul")
    public ResponseEntity<Double> mul(@RequestBody CalculatorDTO calculatorDTO)
    {
        Double result=null;
        result=calculatorDTO.getNum1()*calculatorDTO.getNum2()*calculatorDTO.getNum3()*calculatorDTO.getNum4();
        ResponseEntity<Double> doubleResponseEntity=new ResponseEntity<Double>(result,HttpStatus.OK);
        return doubleResponseEntity;
    }
}
