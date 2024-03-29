package TMA.Order;

import TMA.Item.Item;
import TMA.Item.ItemRepository;
import TMA.Item.ItemService;
import TMA.Order.Order;
import TMA.Order.OrderController;
import TMA.Order.OrderRepository;
import TMA.Order.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void testAcceptOrder() throws Exception {
        Long orderId = 1L;

        Order order = new Order();
        order.setId(orderId);
            order.setQuantity(10);
        when(orderService.getOrderById(orderId)).thenReturn(java.util.Optional.of(order));

        Item item = new Item();
        item.setId(orderId);
        item.setQuantity(20);
        when(itemService.getItemById(orderId)).thenReturn(item);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/acceptOrder/{id}", orderId)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/orders"));
    }
}
