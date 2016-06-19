package com.payworks.test.service;
import com.payworks.test.domain.Superhero;
import com.payworks.test.repository.SuperheroRepository;
import com.payworks.test.web.dto.DetailedSuperHeroDto;
import com.payworks.test.web.dto.SuperheroDto;
import com.payworks.test.web.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SuperheroService {

    @Autowired
    private SuperheroRepository superheroRepository;

    @Autowired
    private HeroPowerService heroPowerService;

    @Transactional
    public Superhero createSuperhero(SuperheroDto superheroDto) {
        Superhero superhero = new Superhero();
        superhero.setName(superheroDto.getName());
        superhero.setPseudonym(superheroDto.getPseudonym());
        superhero.setPublisher(superheroDto.getPublisher());
        superhero.setDateOfFirstAppearance(superheroDto.getDateOfFirstAppearance());

        if (superheroDto.getPowers() != null) {
            for (Long powerId : superheroDto.getPowers()) {
                superhero.getPowers().add(heroPowerService.get(powerId));
            }
        }

        if (superheroDto.getAllies() != null) {
            for (Long alliesId : superheroDto.getAllies()) {
                superhero.getAllies().add(get(alliesId));
            }
        }

        return superheroRepository.save(superhero);
    }

    @Transactional
    public Superhero addPowers(Long superheroId, List<Long> powerIds) {
        Superhero superhero = get(superheroId);

        for (Long powerId : powerIds) {
            superhero.getPowers().add(heroPowerService.get(powerId));
        }

        return superheroRepository.save(superhero);
    }

    @Transactional(readOnly = true)
    public Set<Superhero> getAllies(Long superheroId) {
        Superhero superhero = get(superheroId);
        return superhero.getAllies();
    }

    @Transactional
    public Superhero addAllies(Long superheroId, List<Long> alliesIds) {
        Superhero superhero = get(superheroId);

        for (Long id : alliesIds) {
            superhero.getAllies().add(get(id));
        }

        return superheroRepository.save(superhero);
    }

    @Transactional(readOnly = true)
    public DetailedSuperHeroDto getDetailed(Long id) {
        Superhero superhero = get(id);
        superhero.getAllies().size();
        superhero.getPowers().size();
        return new DetailedSuperHeroDto(superhero);
    }

    public Superhero get(Long id) {
        return Optional.ofNullable(superheroRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException(Superhero.class, id));
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

}
