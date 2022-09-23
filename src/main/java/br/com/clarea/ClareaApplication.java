package br.com.clarea;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.clarea.entity.Agenda;
import br.com.clarea.entity.Role;
import br.com.clarea.entity.constants.ERole;
import br.com.clarea.repository.agenda.AgendaReadRepository;
import br.com.clarea.repository.agenda.AgendaWriteRepository;
import br.com.clarea.repository.role.RoleReadRepository;
import br.com.clarea.repository.role.RoleWriteRepository;

@SpringBootApplication
public class ClareaApplication implements CommandLineRunner {

	@Autowired
	private RoleReadRepository roleReadRepository;

	@Autowired
	private RoleWriteRepository roleWriteRepository;

	@Autowired
	private AgendaWriteRepository agendaWriteRepository;
	
	@Autowired
	private AgendaReadRepository agendaReadRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClareaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Optional<Role> userRole = roleReadRepository.findByRole(ERole.ROLE_USER);
		if (userRole.isEmpty()) {
			roleWriteRepository.save(Role.builder().role(ERole.ROLE_USER).build());
		}
		Optional<Role> adminRole = roleReadRepository.findByRole(ERole.ROLE_ADMIN);
		if (adminRole.isEmpty()) {
			roleWriteRepository.save(Role.builder().role(ERole.ROLE_ADMIN).build());
		}

		Long count = agendaReadRepository.count();
		if(count.intValue() <= 0) {
			int qtdAgendas = 0;
			LocalDateTime dataAgenda = LocalDateTime.now();
			while (qtdAgendas < 20) {
				Agenda agenda = Agenda.builder().dataInicio(dataAgenda).build();
				agenda.setDataFinal(agenda.getDataInicio().plusHours(1));
				agendaWriteRepository.save(agenda);
				qtdAgendas++;
				dataAgenda = agenda.getDataFinal().plusMinutes(15);
			}
		}

	}

}