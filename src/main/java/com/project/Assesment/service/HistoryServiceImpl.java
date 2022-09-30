package com.project.Assesment.service;

import com.project.Assesment.dao.ActivityRepository;
import com.project.Assesment.dao.CustomerRepository;
import com.project.Assesment.dao.HistoryRepository;
import com.project.Assesment.dto.History.InsertHistoryDTO;
import com.project.Assesment.dto.History.UpdateHistoryDTO;
import com.project.Assesment.entity.Activity;
import com.project.Assesment.entity.Customer;
import com.project.Assesment.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService{

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public History getHistory(Long historyId) {
        Optional<History> findHistory = historyRepository.findById(historyId);
        History history = null;
        if (findHistory.isPresent()){
            history = findHistory.get();
        }
        return history;
    }

    @Override
    public UpdateHistoryDTO getUpdateHistory(Long historyId) {
        History findHistory = historyRepository.findById(historyId).get();
        //getHistory(historyId);
        System.out.println("[findHistory : ] "+findHistory);

        UpdateHistoryDTO historyDTO = new UpdateHistoryDTO(
                findHistory.getHistoryId(),
                findHistory.getActivityId(),
                findHistory.getActivity().getVariant().getVariantName(),
                findHistory.getActivity().getCustomer().getName(),
                findHistory.getDiscount(),
                findHistory.getBill()
        );
        System.out.println("[historyDTO : ] "+historyDTO);
        return historyDTO;
    }

    @Override
    public Long insertHistory(InsertHistoryDTO dto, String username) {
        username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByUsername(username);

        BigDecimal discount = new BigDecimal(0);
            if (customer == null){
                return 0L;
            }
            else {
                if (customer.getLevel().equals("Member")){
                    System.out.println("level : " + customer.getLevel());
                    discount = discount.add(new BigDecimal(10));
                }
                else{
                    discount = discount.add(new BigDecimal(30));
                }
            }

        Activity activity = activityRepository.getActivitySuccess(dto.getActivityId(), username);
        System.out.println("[Activity : ] "+activity);

        BigDecimal totalDiscount = activity.getVariant().getPrice().multiply(discount).divide(new BigDecimal(100));
        System.out.println("[total discount : ] "+totalDiscount);

        BigDecimal totalBill = (activity.getVariant().getPrice().subtract(totalDiscount));
        System.out.println("[totalBill : ] "+totalBill);


        BigDecimal balance = new BigDecimal(0);

        if (customer.getBalance().compareTo(totalBill) < 0){
            balance = activity.getCustomer().getBalance();
            return 0L;
        }
        else{
            balance = (activity.getCustomer().getBalance()).subtract(totalBill);
            History newHistory = new History(
                    dto.getActivityId(),
                    discount,
                    totalBill
            );

            activity.getCustomer().setBalance(balance);
            activity.setStatus("Success");
            System.out.println("[balance Customer after pay : ] "+customer.getBalance());

            historyRepository.save(newHistory);
            return newHistory.getHistoryId();
        }
    }
}
