package br.com.alura.screenMatch.Repositorio;

import br.com.alura.screenMatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
}
