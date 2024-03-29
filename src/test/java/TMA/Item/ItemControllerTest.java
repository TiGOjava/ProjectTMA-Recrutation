package TMA.Item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ItemControllerTest {

    @Mock
    private ItemService itemService;

    @Mock
    private Model model;

    @InjectMocks
    private ItemController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testIndex() {
        List<Item> mockItemList = new ArrayList<>();
        mockItemList.add(new Item());
        when(itemService.getAllProducts()).thenReturn(mockItemList);

        String viewName = controller.index(model);

        assertEquals("Coordinator/index", viewName, "Should return the correct view name");
        verify(model).addAttribute("items", mockItemList);
    }

    @Test
    public void testShowIndex() {
        List<Item> mockItemList = new ArrayList<>();
        mockItemList.add(new Item());
        when(itemService.getAllProducts()).thenReturn(mockItemList);

        String viewName = controller.showIndex(model);

        assertEquals("Employee/homeOrders", viewName, "Should return the correct view name");
        verify(model).addAttribute("items", mockItemList);
    }

    @Test
    public void testGetProduct() {
        Item mockItem = new Item();
        mockItem.setId(1L);
        when(itemService.getProductById(1L)).thenReturn(Optional.of(mockItem));

        String viewName = controller.getProduct(1L, model);

        assertEquals("Coordinator/product", viewName, "Should return the correct view name");
        verify(model).addAttribute("item", mockItem);
    }

    @Test
    public void testAddProductForm() {
        String viewName = controller.addProductForm(model);

        assertEquals("Coordinator/add", viewName, "Should return the correct view name");
        verify(model).addAttribute(eq("item"), any(Item.class));
    }

    @Test
    public void testDeleteProduct() {
        Long id = 1L;

        String redirectURL = controller.deleteProduct(id);

        assertEquals("redirect:/items", redirectURL, "Should redirect to /items");
        verify(itemService, times(1)).deleteProduct(id);
    }

    @Test
    public void testEditProductSubmit() {
        Long id = 1L;
        Item item = new Item();
        item.setId(id);

        String redirectURL = controller.editProductSubmit(id, item);

        assertEquals("redirect:/items", redirectURL, "Should redirect to /items");
        verify(itemService, times(1)).saveProduct(item);
    }
    @Test
    public void testSearchItemsByName() {
        String name = "TestName";
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        when(itemService.getItemsByName(name)).thenReturn(items);

        String viewName = controller.searchItemsByName(name, model);

        assertEquals("Coordinator/search", viewName, "Should return the correct view name");
        verify(model).addAttribute("items", items);
    }

    @Test
    public void testSearchItemsByNameEmployee() {
        String name = "TestName";
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        when(itemService.getItemsByName(name)).thenReturn(items);

        String viewName = controller.searchItemsByNameEmployee(name, model);

        assertEquals("Employee/searchEmployee", viewName, "Should return the correct view name");
        verify(model).addAttribute("items", items);
    }
}
