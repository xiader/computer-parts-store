package com.gmail.sasha.myproject.web.controllers;

import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.service.BusinessCardService;
import com.gmail.sasha.myproject.service.service.impl.UserPrincipal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller("controllerBusinessCard")
@RequestMapping("/business-card")
public class BusinessCardController {

    private static Logger logger = LogManager.getLogger(BusinessCardController.class);

    @Autowired
    @Qualifier("businessCardService")
    private BusinessCardService businessCardService;

    @Autowired
    @Qualifier("pageProperties")
    private PageProperties pageProperties;

    @Autowired
    @Qualifier("businessCardValidator")
    private Validator businessCardValidator;



   /* @GetMapping
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

    }*/


    @GetMapping("/cards")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String getBusinessCardPage(
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        List<BusinessCardDTO> businessCardDTOS = businessCardService.findAllByUserEmail(userPrincipal.getUsername());
        modelMap.addAttribute("businessCards", businessCardDTOS);
        return pageProperties.getBusinessCardPagePath();
    }

    @GetMapping("/card")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String getCreateBusinessCardPage(ModelMap modelMap) {
        modelMap.addAttribute("businessCard", new BusinessCardDTO());
        return pageProperties.getBusinessCardCreatePagePath();
    }

    @PostMapping("/card")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public String createBusinessCard(
            @ModelAttribute("businessCard") BusinessCardDTO businessCard,
            ModelMap modelMap,
            BindingResult bindingResult) {
        businessCardValidator.validate(businessCard, bindingResult);
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("businessCard", businessCard);
            return pageProperties.getBusinessCardCreatePagePath();
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String userName = userPrincipal.getUsername();
            businessCardService.saveBusinessCardWithUser(businessCard, userName);
            return "redirect:/business-card/cards";
        }
    }
}
