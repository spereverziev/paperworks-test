package com.payworks.test.web.rest;

import com.payworks.test.domain.Superhero;
import com.payworks.test.service.SuperheroService;
import com.payworks.test.web.dto.DetailedSuperHeroDto;
import com.payworks.test.web.dto.IdsListDto;
import com.payworks.test.web.dto.SuperheroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class SuperheroResource {

    @Autowired
    private SuperheroService superheroService;

    @RequestMapping(value = "/api/superheroes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Superhero> addSuperhero(@Valid @RequestBody SuperheroDto superheroDto) {
        return new ResponseEntity<>(superheroService.createSuperhero(superheroDto), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/api/superheroes/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DetailedSuperHeroDto> getSuperhero(@PathVariable Long id) {
        return ResponseEntity.ok(superheroService.getDetailed(id));
    }

    @RequestMapping(value = "/api/superheroes/{id}/powers", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addPowers(@PathVariable Long id, @Valid @RequestBody IdsListDto idsListDto) {
        return ResponseEntity.ok(superheroService.addPowers(id, idsListDto.getIdList()));
    }

    @RequestMapping(value = "/api/superheroes/{id}/allies", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addAllies(@PathVariable Long id, @Valid @RequestBody IdsListDto idsListDto) {
        return ResponseEntity.ok(superheroService.addAllies(id, idsListDto.getIdList()));
    }

    @RequestMapping(value = "/api/superheroes/{id}/allies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllies(@PathVariable Long id) {
        return ResponseEntity.ok(superheroService.getAllies(id));
    }

    @RequestMapping(value = "/api/superheroes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllSuperheroes() {
        return ResponseEntity.ok(superheroService.getAllSuperheroes());
    }

}
