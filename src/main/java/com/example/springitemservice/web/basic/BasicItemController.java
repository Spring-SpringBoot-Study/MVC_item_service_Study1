package com.example.springitemservice.web.basic;

import com.example.springitemservice.domain.item.Item;
import com.example.springitemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);

        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable("itemId") Long itemId) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    // @PostMapping("/add")
    public String addItmeV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam Integer quantity,
                            Model model) {

        Item item = new Item();

        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItmeV2(@ModelAttribute("item") Item item, Model model) {

        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute("item") Item item 로 하면 "item"이라는 이름으로 model에 값을 넣어줌 - 생략 가능

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItmeV3(@ModelAttribute Item item, Model model) {

        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute Item item 로 하면 Item -> item 으로 첫글자만 소문자로 변환 후 "item"이라는 이름으로 model에 값을 넣어줌

        return "basic/item";
    }

    // @PostMapping("/add")
    public String addItmeV4(Item item, Model model) {

        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute는 생략 가능

        return "basic/item";
    }

    @PostMapping("/add")
    public String addItmeV5(Item item, Model model) { // PRG 문제 해결

        itemRepository.save(item);
        // model.addAttribute("item", item); // @ModelAttribute는 생략 가능

        return "redirect:/basic/items/" + item.getId(); // 상품 등록 후에 상품의 상세 페이지로 GET 요청을 보냄
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /**
     *
     테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }
}
