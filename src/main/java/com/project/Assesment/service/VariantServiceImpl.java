package com.project.Assesment.service;

import com.project.Assesment.dao.VariantRepository;
import com.project.Assesment.dto.variant.InsertVariantDTO;
import com.project.Assesment.dto.variant.UpdateVariantDTO;
import com.project.Assesment.dto.variant.VariantGridDTO;
import com.project.Assesment.entity.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VariantServiceImpl implements VariantService{

    @Autowired
    private VariantRepository variantRepository;

    @Override
    public Long insertVariant(InsertVariantDTO dto) {
        Variant newVariant = new Variant(
                dto.getVariantId(),
                dto.getVariantName(),
                dto.getPrice(),
                dto.getPeriod(),
                dto.getDescription()
        );
        Variant respond = variantRepository.save(newVariant);
        return respond.getVariantId();
    }

    @Override
    public Variant getVariant(Long respondId) {
        Optional<Variant> findVariant = variantRepository.findById(respondId);
        Variant variant = null;
        if (findVariant.isPresent()){
            variant = findVariant.get();
        }
        return variant;
    }

    @Override
    public Variant findVariant(Long variantId) {
        Optional<Variant> findVariant = variantRepository.findById(variantId);
        Variant variant = null;
        if (findVariant.isPresent()){
            variant = findVariant.get();
        }
        return variant;
    }

    @Override
    public Page<Variant> findAll(Pageable pageable) {
        return variantRepository.findAll(pageable);
    }

    @Override
    public UpdateVariantDTO getUpdateVariant(Long variantId) {
        Variant findVariant = variantRepository.findById(variantId).get();
        UpdateVariantDTO variantDTO = new UpdateVariantDTO(
                findVariant.getVariantId(),
                findVariant.getVariantName(),
                findVariant.getPrice(),
                findVariant.getPeriod(),
                findVariant.getDescription()
        );
        return variantDTO;
    }

    @Override
    public Long updateVariant(UpdateVariantDTO dto) {
        Optional<Variant> findVariant = variantRepository.findById(dto.getVariantId());
        Variant variant = null;
        if (findVariant.isPresent()){
            variant = findVariant.get();
        }
        Variant updateVariant = new Variant(
                dto.getVariantId(),
                dto.getVariantName(),
                dto.getPrice(),
                dto.getPeriod(),
                dto.getDescription()
        );
        variantRepository.save(updateVariant);
        return updateVariant.getVariantId();
    }

    @Override
    public Integer dependentVariant(Long variantId) {
        Integer totalDependentRoom = variantRepository.countByVariantId(variantId);
        return totalDependentRoom;
    }

    @Override
    public void deleteVariant(Long variantId) {
        variantRepository.deleteById(variantId);
    }
}
