package com.project.Assesment.rest;

import com.project.Assesment.dao.HistoryRepository;
import com.project.Assesment.dto.History.InsertHistoryDTO;
import com.project.Assesment.dto.History.UpdateHistoryDTO;
import com.project.Assesment.dto.activity.InsertActivityDTO;
import com.project.Assesment.dto.activity.UpdateActivityDTO;
import com.project.Assesment.entity.Activity;
import com.project.Assesment.entity.Customer;
import com.project.Assesment.entity.History;
import com.project.Assesment.exceptionHandler.ErrorResponse;
import com.project.Assesment.exceptionHandler.IdNotFoundException;
import com.project.Assesment.exceptionHandler.NotEnoughBalanceException;
import com.project.Assesment.service.ActivityService;
import com.project.Assesment.service.CustomerService;
import com.project.Assesment.service.HistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class HistoryRestController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CustomerService customerService;

    // -- GET SINGLE HISTORY BY HISTORY ID
    @Operation(summary = "Mendapatkan satu data History.", description = "Satu data History berdasarkan History ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the History",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = History.class))}),
            @ApiResponse(responseCode = "404", description = "History Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/history/{historyId}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true) Long historyId){
        History history = historyService.getHistory(historyId);
        if (history == null){
            throw new IdNotFoundException("History with ID "+historyId+" not found.");
        }
        UpdateHistoryDTO dto = historyService.getUpdateHistory(historyId);
        System.out.println("[UpdateHistoryDTO : ] "+dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // -- CREATE NEW HISTORY
    @Operation(summary = "Membuat data History baru.", description = "Aktivitas history terbaru.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Added New History",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = History.class))}),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be  Null!",
                    content = @Content)
    })
    @PostMapping("/history/insert")
    public ResponseEntity<Object> post(@Valid @RequestBody InsertHistoryDTO dto){
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = customerService.findCustomer(username);
            if (customer == null){
                throw new UsernameNotFoundException("Customer with Username "+username+" not found");
            }
            Long respondId = historyService.insertHistory(dto, username);
            if (respondId == 0){
                throw new NotEnoughBalanceException("Your balance is not enough to buy this Variant. Please add your balance before.");
            }
            else {
                History newHistory = historyService.getHistory(respondId);
                System.out.println("[newHistory : ] "+newHistory);
                return ResponseEntity.status(HttpStatus.CREATED).body(newHistory);
            }
    }
}
