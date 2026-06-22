package com.flowershop.repository;

import com.flowershop.model.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Long> {
    List<Flower> findAllByOrderByCreatedAtDesc();

    List<Flower> findByCategoryIdOrderByCreatedAtDesc(Long categoryId);

    @Query("SELECT f FROM Flower f WHERE " +
           "(:categoryId IS NULL OR f.category.id = :categoryId) AND " +
           "(:keyword IS NULL OR f.name LIKE %:keyword% OR f.description LIKE %:keyword%)")
    List<Flower> searchFlowers(@Param("categoryId") Long categoryId,
                               @Param("keyword") String keyword);
}
