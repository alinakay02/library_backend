package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.ItemDto;
import uz.library.takm.model.Item;
import uz.library.takm.repository.ItemRepository;
import uz.library.takm.service.ItemService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    // Получение списка всех новостей
    @GetMapping("/getAll")
    public List<ItemDto> getAllNews() {
        return itemService.findAllItems();
    }

    // Добавление новости
    @PostMapping("/add")
    public Item addItem(@RequestBody ItemDto itemDto) {
        return itemService.addItem(itemDto);
    }

    // Получение новости по ID
    @GetMapping("/{id}")
    public Optional<ItemDto> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id);
    }

    // Обновление новости
    @PutMapping("/update")
    public Item updateItem(@RequestBody ItemDto itemDto) {
        return itemService.updateItem(itemDto);
    }
}
