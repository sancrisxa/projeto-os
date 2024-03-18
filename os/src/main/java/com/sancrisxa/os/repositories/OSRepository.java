package com.sancrisxa.os.repositories;

import com.sancrisxa.os.domain.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OSRepository extends JpaRepository<OS, Integer> {

}
