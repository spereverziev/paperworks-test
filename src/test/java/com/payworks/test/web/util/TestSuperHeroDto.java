package com.payworks.test.web.util;

import com.payworks.test.domain.Publisher;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class TestSuperHeroDto {

    @NotNull
    private Publisher publisher;

    @NotBlank
    private String name;

    @NotBlank
    private String pseudonym;

    private String dateOfFirstAppearance;

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

    public String getDateOfFirstAppearance() {
        return dateOfFirstAppearance;
    }

    public void setDateOfFirstAppearance(String dateOfFirstAppearance) {
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
