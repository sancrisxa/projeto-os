package com.sancrisxa.os;

import com.sancrisxa.os.domain.Cliente;
import com.sancrisxa.os.domain.OS;
import com.sancrisxa.os.domain.Tecnico;
import com.sancrisxa.os.domain.enums.Prioridade;
import com.sancrisxa.os.domain.enums.Status;
import com.sancrisxa.os.repositories.ClienteRepository;
import com.sancrisxa.os.repositories.OSRepository;
import com.sancrisxa.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsApplication.class, args);
	}
}
