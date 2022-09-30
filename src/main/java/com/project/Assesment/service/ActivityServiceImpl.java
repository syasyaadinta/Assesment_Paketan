package com.project.Assesment.service;

import com.project.Assesment.dao.ActivityRepository;
import com.project.Assesment.dao.CustomerRepository;
import com.project.Assesment.dao.VariantRepository;
import com.project.Assesment.dto.activity.ActivityDTO;
import com.project.Assesment.dto.activity.InsertActivityDTO;
import com.project.Assesment.dto.activity.UpdateActivityDTO;
import com.project.Assesment.dto.variant.UpdateVariantDTO;
import com.project.Assesment.entity.Activity;
import com.project.Assesment.entity.Customer;
import com.project.Assesment.entity.History;
import com.project.Assesment.entity.Variant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ActivityServiceImpl implements ActivityService{

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Override
    public Long insertActivity(InsertActivityDTO dto, String username) {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null){
            return 0L;
        }
        Variant variant = variantRepository.findById(dto.getVariantId()).get();
        Activity newActivity = new Activity(
                dto.getActivityId(),
                customer.getCustomerId(),
                dto.getVariantId(),
                variant.getPrice(),
                LocalDate.now(),
                "Pending"
        );
        activityRepository.save(newActivity);
        return newActivity.getActivityId();
    }

    @Override
    public Activity getActivity(Long activityId) {
        Optional<Activity> findActivity = activityRepository.findById(activityId);
        Activity activity = null;
        if (findActivity.isPresent()){
            activity = findActivity.get();
        }
        return activity;
    }

    @Override
    public UpdateActivityDTO getUpdateActivity(Long activityId) {
        Activity findActivity = activityRepository.findById(activityId).get();
        UpdateActivityDTO activityDTO = new UpdateActivityDTO(
                findActivity.getActivityId(),
                findActivity.getCustomer().getName(),
                findActivity.getVariant().getVariantId(),
                findActivity.getBuyDate(),
                findActivity.getStatus()
        );
        return activityDTO;
    }

    @Override
    public Long updateActivity(UpdateActivityDTO dto) {
        Optional<Activity> findActivity = activityRepository.findById(dto.getActivityId());
        Activity activity = null;
        if (findActivity.isPresent()){
            activity = findActivity.get();
        }
        Activity updateActivity = new Activity(
                dto.getActivityId(),
                activity.getCustomer().getCustomerId(),
                dto.getVariantId(),
                activity.getVariant().getPrice(),
                dto.getBuyDate(),
                "Canceled"
        );
        Activity update  = activityRepository.save(updateActivity);
        return update.getActivityId();
    }

    @Override
    public Page<Activity> findAllPage(Pageable pageable) {
        return activityRepository.findAllPage(pageable);
    }

}
