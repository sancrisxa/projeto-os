package com.sancrisxa.os.repositories;

import com.sancrisxa.os.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

    @Query("SELECT tecnico FROM Tecnico tecnico WHERE tecnico.cpf =:cpf")
    Tecnico findByCPF(@Param("cpf") String cpf);
}
