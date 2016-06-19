package com.payworks.test.service;

import com.payworks.test.domain.HeroPower;
import com.payworks.test.repository.HeroPowerRepository;
import com.payworks.test.web.dto.HeroPowerDto;
import com.payworks.test.web.error.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeroPowerService {

    @Autowired
    private HeroPowerRepository heroPowerRepository;

    public HeroPower addPower(HeroPowerDto heroPowerDto) {
        HeroPower heroPower = new HeroPower();
        heroPower.setName(heroPowerDto.getName());
        heroPower.setDescription(heroPowerDto.getDescription());
        return heroPowerRepository.save(heroPower);
    }

    public List<HeroPower> getAll() {
        return heroPowerRepository.findAll();
    }

    public HeroPower get(Long id) {
        return Optional.ofNullable(heroPowerRepository.findOne(id))
                .orElseThrow(() -> new EntityNotFoundException(HeroPower.class, id));
    }
}
