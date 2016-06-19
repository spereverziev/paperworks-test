package com.payworks.test.web.dto;

import com.payworks.test.domain.HeroPower;
import com.payworks.test.domain.Publisher;
import com.payworks.test.domain.Superhero;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DetailedSuperHeroDto {

    private Long id;

    private String name;

    private String pseudonym;

    private Publisher publisher;

    private LocalDate dateOfFirstAppearance;

    private Set<HeroPower> powers = new HashSet<>();

    private Set<Superhero> allies = new HashSet<>();

    public DetailedSuperHeroDto(Superhero superhero) {
        this.id = superhero.getId();
        this.name = superhero.getName();
        this.pseudonym = superhero.getPseudonym();
        this.publisher = superhero.getPublisher();
        this.dateOfFirstAppearance = superhero.getDateOfFirstAppearance();
        this.powers = superhero.getPowers();
        this.allies = superhero.getAllies();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public LocalDate getDateOfFirstAppearance() {
        return dateOfFirstAppearance;
    }

    public void setDateOfFirstAppearance(LocalDate dateOfFirstAppearance) {
        this.dateOfFirstAppearance = dateOfFirstAppearance;
    }

    public Set<HeroPower> getPowers() {
        return powers;
    }

    public void setPowers(Set<HeroPower> powers) {
        this.powers = powers;
    }

    public Set<Superhero> getAllies() {
        return allies;
    }

    public void setAllies(Set<Superhero> allies) {
        this.allies = allies;
    }
}
