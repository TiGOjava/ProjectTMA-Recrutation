package TMA.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class ItemService {

        @Autowired
        private ItemRepository itemRepository;

        public List<Item> getAllProducts() {
            return itemRepository.findAll();
        }

        public Item getItemById(Long id) {
            Optional<Item> optionalItem = itemRepository.findById(id);
            return optionalItem.orElseThrow(() -> new NoSuchElementException("Item not found with id: " + id));
        }

        public void saveProduct(Item item) {
            itemRepository.save(item);
        }

        public void deleteProduct(Long id) {
            itemRepository.deleteById(id);
        }

        public List<Item> getItemsByName(String name) {
            return itemRepository.findByNameContaining(name);
        }

        public Optional<Item> getProductById(Long id) {
            return itemRepository.findById(id);
        }

        public Optional<Item> getItemByName(String name) {
            return itemRepository.findByName(name);
        }

        public void saveItem(Item item) {
            itemRepository.save(item);
        }
    }


