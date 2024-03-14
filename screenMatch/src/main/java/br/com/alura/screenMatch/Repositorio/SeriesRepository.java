package br.com.alura.screenMatch.Repositorio;

import br.com.alura.screenMatch.model.Categoria;
import br.com.alura.screenMatch.model.Episodio;
import br.com.alura.screenMatch.model.Serie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Serie, Long> {
    Optional<Serie> findByTituloContainingIgnoreCase(String seriePesquisada);
    List<Serie> findByAtoresContainingIgnoreCaseAndAvaliacoesGreaterThanEqual(String AtorPesquisado, Double avaliacao);
    List<Serie> findTop5ByOrderByAvaliacoesDesc();
    List<Serie> findByGenero(Categoria categoria);

    List<Serie> findByTotalTemporadasLessThanEqualAndAvaliacoesGreaterThanEqual(Integer temporadas, Double avaliacao);

    @Query("Select s from Serie s where s.totalTemporadas <= :temporadas and s.avaliacoes >= :avaliacao")
    List<Serie> SeriePorTemporada(Integer temporadas, Double avaliacao);

    @Query("Select s from Serie s JOIN s.episodios e where e.titulo ilike %:trechoBuscado%")
    List<Episodio> episodioPesquisadoPorTrecho(String trechoBuscado);

    @Query("Select e from Serie s Join s.episodios e where s = :serie ORDER BY e.avaliacao DESC LIMIT 5")
    List<Episodio> topEpisodiosPorSerie(Serie serie);

    @Query("Select e from Serie s JOIN s.episodios e where s = :serie AND YEAR(dataLancamento) >= :ano")
    List<Episodio> episodiosPorSerieEAno(Serie serie, int ano);
}
