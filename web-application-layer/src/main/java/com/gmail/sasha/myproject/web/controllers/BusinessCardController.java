package com.gmail.sasha.myproject.web.controllers;

import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/business-card")
public class BusinessCardController {

    @Autowired
    @Qualifier("businessCardService")
    private BusinessCardService businessCardService;

    @GetMapping
    private ResponseEntity<?> getAllBusinessCards(ModelMap modelMap){
        List<BusinessCardDTO> businessCards = businessCardService.getAllBusinessCards();
        System.out.println(businessCards);
       // modelMap.addAttribute("businessCards", businessCards);
        //return "businessCard";
       return new ResponseEntity<>(businessCards, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveBussinesCard(BusinessCardDTO businessCardDTO){
       return new ResponseEntity<>(businessCardService.saveBusinessCard(businessCardDTO), HttpStatus.OK);

    }
}
