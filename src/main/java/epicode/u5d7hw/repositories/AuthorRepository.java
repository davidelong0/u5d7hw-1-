package epicode.u5d7hw.repositories;

import epicode.u5d7hw.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
