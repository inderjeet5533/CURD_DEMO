package it.gruppogaspari.personalinfo.repository;

import it.gruppogaspari.personalinfo.entity.BasicInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasicInfoRepository extends JpaRepository<BasicInfoEntity, Integer> {

}
