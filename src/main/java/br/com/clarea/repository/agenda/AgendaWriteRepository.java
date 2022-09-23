package br.com.clarea.repository.agenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.clarea.entity.Agenda;

@Repository
public interface AgendaWriteRepository extends JpaRepository<Agenda, Long>, AgendaWriteRepositoryCustom {
}
