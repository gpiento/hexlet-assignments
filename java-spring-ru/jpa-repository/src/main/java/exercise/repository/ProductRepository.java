package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetween(int min, int max, Sort sort);

    List<Product> findByPriceBetween(int min, int max);

    List<Product> findByPriceGreaterThanEqual(int min, Sort sort);

    List<Product> findByPriceGreaterThanEqual(int min);

    List<Product> findByPriceLessThanEqual(int max, Sort sort);

    List<Product> findByPriceLessThanEqual(int max);

    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int min, int max, Sort sort);

    List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(int min, int max);

    // END
}
