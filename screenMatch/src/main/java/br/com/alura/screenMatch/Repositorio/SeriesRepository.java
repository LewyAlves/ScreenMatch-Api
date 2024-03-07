package br.com.alura.screenMatch.Repositorio;

import br.com.alura.screenMatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String seriePesquisada);
    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacoesGreaterThanEqual(String AtorPesquisado, Double avaliacao);

    List<Serie> findTop5ByOrderByAvaliacoesDesc();
}
