package com.gmail.sasha.myproject.web.controllers.api;

import com.gmail.sasha.myproject.service.model.BusinessCardDTO;
import com.gmail.sasha.myproject.service.service.BusinessCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cards")
public class BusinessCardApiController {

    @Autowired
    @Qualifier("businessCardService")
    private BusinessCardService cardService;


    @GetMapping(value = "/user/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public List<BusinessCardDTO> getBusinessCardsByUserId(
            @PathVariable("id") Long id
    ) {
        return cardService.getAllByUserId(id);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public ResponseEntity<?> deleteBusinessCard(
            @PathVariable(name = "id") Long id
    ) {
        cardService.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public BusinessCardDTO getOneBusinessCardsById(
            @PathVariable("id") Long id) {
        return cardService.getOneById(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MANAGE_BUSINESS_CARD')")
    public List<BusinessCardDTO> findAllBusinessCards() {
        return cardService.getAllBusinessCards();
    }
}
