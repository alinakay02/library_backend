package uz.library.takm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.library.takm.dto.ItemDto;
import uz.library.takm.model.Item;
import uz.library.takm.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    // Конструктор для внедрения зависимости репозитория
    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Метод для получения всех новостей, возвращает список DTO
    @Transactional(readOnly = true)
    public List<ItemDto> findAllNews() {
        return itemRepository.findAll().stream()
            .map(this::convertToItemDto) // Преобразование каждой сущности в DTO
            .collect(Collectors.toList());
    }

    // Вспомогательный метод для преобразования сущности Item в ItemDto
    private ItemDto convertToItemDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setTitle(item.getTitle());
        dto.setNews(item.getNews());
        dto.setDate(item.getDate());
        dto.setPhoto(item.getPhoto());
        return dto;
    }
}
