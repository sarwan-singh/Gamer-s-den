package com.smurf.pdfdownloaderapi.Repository;

import com.smurf.pdfdownloaderapi.model.Gamer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GamerRepository extends CrudRepository<Gamer, Integer> {

    public Gamer findGamerByName(String name);

    public boolean existsByName(String name);


}
