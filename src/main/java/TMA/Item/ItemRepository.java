package TMA.Item;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByNameContaining(String name);
    Optional<Item> findById(Long id);

    Optional<Item> findByName(String name);
}
