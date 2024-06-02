package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.ItemDto;
import uz.library.takm.model.Item;
import uz.library.takm.repository.ItemRepository;
import uz.library.takm.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class NewsController {

    private final ItemService itemService;
    private final ItemRepository itemRepository;

    @Autowired
    public NewsController(ItemService itemService, ItemRepository itemRepository) {
        this.itemService = itemService;
        this.itemRepository = itemRepository;
    }

    // Получение списка всех новостей
    @GetMapping("/getAll")
    public List<Item> getAllNews() {
        return (List<Item>) itemRepository.findAll();
    }

    // Добавление новости
    @PostMapping("/add")
    public Item addItem(@RequestBody ItemDto itemDto) {
        return itemService.addItem(itemDto);
    }
}
