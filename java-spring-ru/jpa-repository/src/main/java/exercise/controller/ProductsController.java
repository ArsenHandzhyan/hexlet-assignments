package exercise.controller;

import exercise.exception.ResourceNotFoundException;
import exercise.model.Product;
import exercise.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping
    public List<Product> index(@RequestParam(required = false) Integer min, @RequestParam(required = false) Integer max) {
        var sort = Sort.by(Sort.Order.asc("price"));

        // Если ни один из параметров не передан, вернуть все товары
        if (min == null && max == null) {
            return productRepository.findAll(sort);
        }

        // Получить максимальную цену товара из базы данных, если передан только минимальный параметр
        if (min != null && max == null) {
            Integer maxPrice = productRepository.findMaxPrice();
            return productRepository.findByPriceBetween(min, maxPrice, sort);
        }

        // Получить минимальную цену товара из базы данных, если передан только максимальный параметр
        if (min == null) {
            Integer minPrice = productRepository.findMinPrice();
            return productRepository.findByPriceBetween(minPrice, max, sort);
        }

        // Вернуть товары в заданном диапазоне цен
        return productRepository.findByPriceBetween(min, max, sort);
    }
// END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
