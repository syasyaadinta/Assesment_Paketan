package com.project.Assesment.service;

import com.project.Assesment.dto.variant.InsertVariantDTO;
import com.project.Assesment.dto.variant.UpdateVariantDTO;
import com.project.Assesment.dto.variant.VariantGridDTO;
import com.project.Assesment.entity.Variant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VariantService {
    Long insertVariant(InsertVariantDTO dto);

    Variant getVariant(Long respondId);

    Variant findVariant(Long variantId);

    Page<Variant> findAll(Pageable pageable);

    UpdateVariantDTO getUpdateVariant(Long variantId);

    Long updateVariant(UpdateVariantDTO dto);

    Integer dependentVariant(Long variantId);

    void deleteVariant(Long variantId);
}
