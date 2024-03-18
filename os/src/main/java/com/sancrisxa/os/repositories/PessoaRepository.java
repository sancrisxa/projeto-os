package com.sancrisxa.os.repositories;

import com.sancrisxa.os.domain.Pessoa;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
