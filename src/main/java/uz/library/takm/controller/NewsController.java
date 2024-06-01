package uz.library.takm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.library.takm.dto.ItemDto;
import uz.library.takm.model.Item;
import uz.library.takm.repository.ItemRepository;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    private ItemRepository itemRepository;

    // Получение списка всех новостей
    @GetMapping("/news/getAll")
    public List<Item> getAllNews() {
        return itemRepository.findAll();
    }
}
