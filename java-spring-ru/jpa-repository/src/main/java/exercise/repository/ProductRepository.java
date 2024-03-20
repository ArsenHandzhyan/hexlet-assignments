package exercise.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice, Sort sort);
    @Query("SELECT MAX(p.price) FROM Product p")

    Integer findMaxPrice();
    @Query("SELECT MIN(p.price) FROM Product p")

    Integer findMinPrice();
    // END
}
