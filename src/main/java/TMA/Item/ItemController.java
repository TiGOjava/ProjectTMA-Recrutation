package TMA.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {



    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }


    @GetMapping("/items")
    public String index(Model model) {
        model.addAttribute("items", itemService.getAllProducts());
        return "Coordinator/index";
    }



    @GetMapping("/home")
    public String showIndex(Model model) {
        model.addAttribute("items", itemService.getAllProducts());
        return "Employee/homeOrders";
    }



    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable Long id, Model model) {
        itemService.getProductById(id).ifPresent(item -> model.addAttribute("item", item));
        return "Coordinator/product";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("item", new Item());
        return "Coordinator/add";
    }

    @PostMapping("/add")
    public String addProductSubmit(@ModelAttribute Item item) {
        itemService.saveProduct(item);
        return "redirect:/items";
    }

    @GetMapping("/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Item> optionalItem = itemService.getProductById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            model.addAttribute("item", item);
            model.addAttribute("itemGroups", TMA.Item.ItemGroup.values());
            model.addAttribute("units", TMA.Item.Unit.values());
            model.addAttribute("statuses", TMA.Item.Status.values());
            return "Coordinator/edit";
        } else {
            model.addAttribute("errorMessage", "Item with id " + id + " not found.");
            return "error";
        }
    }

    @PostMapping("/edit/{id}")
    public String editProductSubmit(@PathVariable Long id, @ModelAttribute Item item) {
        itemService.saveProduct(item);
        return "redirect:/items";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        itemService.deleteProduct(id);
        return "redirect:/items";
    }

    @GetMapping("/search")
    public String searchItemsByName(@RequestParam String name, Model model) {
        List<Item> items = itemService.getItemsByName(name);
        model.addAttribute("items", items);
        return "Coordinator/search";
    }

    @GetMapping("/searchItemEmployee")
    public String searchItemsByNameEmployee(@RequestParam String name, Model model) {
        List<Item> items = itemService.getItemsByName(name);
        model.addAttribute("items", items);
        return "Employee/searchEmployee";
    }



}