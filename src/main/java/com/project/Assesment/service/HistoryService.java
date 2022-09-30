package com.project.Assesment.service;

import com.project.Assesment.dto.History.InsertHistoryDTO;
import com.project.Assesment.dto.History.UpdateHistoryDTO;
import com.project.Assesment.entity.History;

public interface HistoryService {
    History getHistory(Long historyId);

    UpdateHistoryDTO getUpdateHistory(Long historyId);

    Long insertHistory(InsertHistoryDTO dto, String username);
}
