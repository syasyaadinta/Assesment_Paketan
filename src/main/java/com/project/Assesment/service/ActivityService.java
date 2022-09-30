package com.project.Assesment.service;

import com.project.Assesment.dto.activity.InsertActivityDTO;
import com.project.Assesment.dto.activity.UpdateActivityDTO;
import com.project.Assesment.dto.variant.UpdateVariantDTO;
import com.project.Assesment.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityService {
    Long insertActivity(InsertActivityDTO dto, String username);

    Activity getActivity(Long activityId);

    UpdateActivityDTO getUpdateActivity(Long activityId);

    Long updateActivity(UpdateActivityDTO dto);

    Page<Activity> findAllPage(Pageable pageable);
}
