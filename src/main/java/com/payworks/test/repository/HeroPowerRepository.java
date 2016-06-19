package com.payworks.test.repository;

import com.payworks.test.domain.HeroPower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroPowerRepository extends JpaRepository<HeroPower, Long> {

}
