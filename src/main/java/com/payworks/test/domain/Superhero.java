package com.payworks.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "super_hero")
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String pseudonym;

    @Column
    private Publisher publisher;

    @Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
    private LocalDate dateOfFirstAppearance;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "superhero_pwers",
            joinColumns = {@JoinColumn(name = "hero_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "power_id", referencedColumnName = "id")})
    private Set<HeroPower> powers = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "superhero_allies",
            joinColumns = {@JoinColumn(name = "hero_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ally_id", referencedColumnName = "id")})
    private Set<Superhero> allies = new HashSet<>();

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
