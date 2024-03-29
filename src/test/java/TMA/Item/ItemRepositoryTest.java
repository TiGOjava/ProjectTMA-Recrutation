package TMA.Item;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ItemRepositoryTest {

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void testFindByNameContaining() {
        String name = "Test";
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        when(itemRepository.findByNameContaining(name)).thenReturn(items);

        List<Item> result = itemRepository.findByNameContaining(name);

        assertEquals(items.size(), result.size(), "Should return a list of items containing the given name");
        verify(itemRepository, times(1)).findByNameContaining(name);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Item item = new Item();
        when(itemRepository.findById(id)).thenReturn(Optional.of(item));

        Optional<Item> result = itemRepository.findById(id);

        assertEquals(Optional.of(item), result, "Should return an optional containing the item with the given id");
        verify(itemRepository, times(1)).findById(id);
    }

    @Test
    public void testFindByName() {
        String name = "Test";
        Item item = new Item();
        when(itemRepository.findByName(name)).thenReturn(Optional.of(item));

        Optional<Item> result = itemRepository.findByName(name);

        assertEquals(Optional.of(item), result, "Should return an optional containing the item with the given name");
        verify(itemRepository, times(1)).findByName(name);
    }
}

