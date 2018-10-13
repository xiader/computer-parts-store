package com.gmail.sasha.myproject.web.controllers.api;

import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/business-cards")
public class BusinessCardApiController {

    @Autowired
    @Qualifier("businessCardService")
    private BusinessCardService cardService;


    @GetMapping(value = "/user/{id}/cards")
    @ResponseBody
    public List<BusinessCardDTO> getBusinessCardsByUserId(@PathVariable("id") Long id) {
        return cardService.getAllByUserId(id);
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public BusinessCardDTO getOneBusinessCardsById(@PathVariable("id") Long id) {
        return cardService.getOneById(id);
    }


    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    public ResponseEntity<?> deleteBusinessCard
            (@PathVariable(name = "id") Long id
            ) {
        cardService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_BUISNESS_CARD')")
    @ResponseBody
    public List<BusinessCardDTO> findAllBusinessCards() {
        return cardService.getAllBusinessCards();
    }
}
