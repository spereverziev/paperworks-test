package com.payworks.test.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.payworks.test.domain.Publisher;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class SuperheroDto {

    @NotNull
    private Publisher publisher;

    @NotBlank
    private String name;

    @NotBlank
    private String pseudonym;

    private LocalDate dateOfFirstAppearance;

    private List<Long> allies;

    private List<Long> powers;

    public List<Long> getAllies() {
        return allies;
    }

    public void setAllies(List<Long> allies) {
        this.allies = allies;
    }

    public List<Long> getPowers() {
        return powers;
    }

    public void setPowers(List<Long> powers) {
        this.powers = powers;
    }

    public LocalDate getDateOfFirstAppearance() {
        return dateOfFirstAppearance;
    }

    public void setDateOfFirstAppearance(LocalDate dateOfFirstAppearance) {
        this.dateOfFirstAppearance = dateOfFirstAppearance;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
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
}
