package com.project.Assesment.dao;

import com.project.Assesment.entity.Variant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VariantRepository extends JpaRepository<Variant, Long> {

    @Query(value = """
            SELECT COUNT(*)
            FROM History AS his
                LEFT JOIN Variant AS var ON his.VariantId = var.VariantId
                LEFT JOIN Activity AS act ON his.ActivityId = act.ActivityId
            WHERE var.VariantId = :variantId
            """, nativeQuery = true)
    Integer countByVariantId(@Param("variantId") Long variantId);
}
