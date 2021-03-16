package home.sabapathy.gc;

import javassist.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroRepository extends JpaRepository<Hero, Long> {
    Optional<Hero> findByName(String any);
}
