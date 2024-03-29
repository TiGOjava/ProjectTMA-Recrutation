package TMA.Order;

import TMA.Item.Item;
import TMA.Item.ItemRepository;
import TMA.Item.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.*;

@Controller
public class OrderController {


    OrderRepository orderRepository;

    ItemRepository itemRepository;

    OrderService orderService;

    ItemService itemService;



    public OrderController(OrderRepository orderRepository, ItemRepository itemRepository, OrderService orderService, ItemService itemService) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderService = orderService;
        this.itemService = itemService;
    }


    @PostMapping("/order")
    public String addOrderSubmit(@ModelAttribute Order order, Model model) {
        try {
            orderService.saveOrder(order);
            model.addAttribute("message", "Request created");
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/errorOrders";
        }
        return "redirect:/home";
    }

    @GetMapping("/orders")
    public String showOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "Coordinator/orders";
    }

    @GetMapping("/getProductDetails")
    @ResponseBody
    public ResponseEntity<?> getProductDetails(@RequestParam String name) {
        Optional<Item> optionalItem = itemService.getItemByName(name);
        return optionalItem.map(item -> {
            Map<String, Object> response = new HashMap<>();
            response.put("id", item.getId());
            response.put("unit", item.getUnit());
            response.put("priceNetto", item.getPriceNetto());
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/searchOrder")
        public String searchOrderById(@RequestParam Long id, Model model) {
        Optional<Order> optionalOrder = orderService.getOrderById(id);
        optionalOrder.ifPresent(order -> model.addAttribute("order", order));
        if (optionalOrder.isEmpty()) {
            model.addAttribute("errorMessage", "Order not found");
        }
        return "Coordinator/searchOrders";
    }

    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "redirect:/orders";
    }


    @PostMapping("/acceptOrder/{id}")
    public String acceptOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        Item item = itemService.getItemById(id);

        double orderQuantity = order.getQuantity();
        double itemQuantity = item.getQuantity();

        if (itemQuantity >= orderQuantity) {
            item.setQuantity(itemQuantity - orderQuantity);
            itemService.saveItem(item);
            orderService.deleteOrder(id);
        } else {
                    return "redirect:/errorOrder";
        }

        return "redirect:/orders";
    }



    @GetMapping("/errorOrder")
    public String showErrorPage(Model model) {
        return "Employee/error";
    }

    @GetMapping("/success")
    public String showSuccessPage(Model model) {
        return "Coordinator/success";
    }


    @GetMapping("/order")
    public String orderForm(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("item", new Item());
        return "Employee/order";
    }


    @GetMapping("/searchEmployee")
    public String searchItemsEmployeeByName(@RequestParam String name, Model model) {
        List<Item> items = itemService.getItemsByName(name);
        model.addAttribute("items", items);
        return "Coordinator/searchOrders";
    }

}
