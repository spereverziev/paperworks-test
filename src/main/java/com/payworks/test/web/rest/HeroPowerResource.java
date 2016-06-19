package com.payworks.test.web.rest;

import com.payworks.test.domain.HeroPower;
import com.payworks.test.domain.Superhero;
import com.payworks.test.service.HeroPowerService;
import com.payworks.test.web.dto.HeroPowerDto;
import com.payworks.test.web.dto.SuperheroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class HeroPowerResource {

    @Autowired
    private HeroPowerService heroPowerService;

    @RequestMapping(value = "/api/heropowers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HeroPower> addSuperhero(@Valid @RequestBody HeroPowerDto heroPowerDto) {
        return new ResponseEntity<>(heroPowerService.addPower(heroPowerDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/heropowers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<HeroPower>> addSuperhero() {
        return ResponseEntity.ok(heroPowerService.getAll());
    }

}
