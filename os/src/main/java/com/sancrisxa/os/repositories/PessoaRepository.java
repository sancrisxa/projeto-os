package com.sancrisxa.os.repositories;

import com.sancrisxa.os.domain.Pessoa;
import com.sancrisxa.os.domain.Tecnico;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    @Query("SELECT pessoa FROM Pessoa pessoa WHERE pessoa.cpf =:cpf")
    Pessoa findByCPF(@Param("cpf") String cpf);
}
