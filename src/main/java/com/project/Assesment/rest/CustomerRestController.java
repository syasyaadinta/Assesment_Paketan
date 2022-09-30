package com.project.Assesment.rest;

import com.project.Assesment.dto.account.AccountRegisterDTO;
import com.project.Assesment.dto.customer.UpdateCustomerDTO;
import com.project.Assesment.entity.Customer;
import com.project.Assesment.exceptionHandler.ErrorResponse;
import com.project.Assesment.service.AccountService;
import com.project.Assesment.service.CustomerService;
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
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    // -- CREATE NEW CUSTOMER
    @Operation(summary = "Membuat data Customer baru.", description = "Data customer baru.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Added New Customer",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be  Null!",
                    content = @Content)
    })
    @PostMapping("/customer")
    public ResponseEntity<Object> post(@Valid @RequestBody AccountRegisterDTO dto){
        try{
            Long respondId = customerService.registerCustomerAccount(dto);
            Customer newCust = customerService.getCustomer(respondId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCust);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.class);
        }
    }

    // -- GET SINGLE ADMIN BY USERNAME AUTHENTICATION
    @Operation(summary = "Mendapatkan satu data Customer.", description = "Satu data Customer berdasarkan User Auth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Customer",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/customer/my-profile")
    public ResponseEntity<Object> getUpdate(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomer(username);
        UpdateCustomerDTO dto = customerService.getUpdateCustomer(username);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // -- UPDATE CUSTOMER BY USER AUTH
    @Operation(summary = "Mengupdate Customer yang sudah ada.", description = "Response akan mengembalikan informasi data yang baru saja diupdate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Updated Customer",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be Null!",
                    content = @Content)
    })
    @PutMapping("/customer/my-profile")
    public ResponseEntity<Object> put(@Valid @RequestBody UpdateCustomerDTO dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            Long respondId = customerService.updateCustomer(dto);
            Customer updateCust = customerService.getCustomer(respondId);
            return ResponseEntity.status(HttpStatus.OK).body(updateCust);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.class);
        }
    }

    // -- UPDATE CUSTOMER BY USER AUTH
    @Operation(summary = "Mengupdate Customer yang sudah ada.", description = "Response akan mengembalikan informasi data yang baru saja diupdate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Updated Customer",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Customer.class))}),
            @ApiResponse(responseCode = "404", description = "Customer Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be Null!",
                    content = @Content)
    })
    @PutMapping("/customer/my-profile/add-balance")
    public ResponseEntity<Object> addBalance(@Valid @RequestBody UpdateCustomerDTO dto){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try{
            Long respondId = customerService.updateCustomerBalance(dto, username);
            Customer updateCust = customerService.getCustomer(respondId);
            return ResponseEntity.status(HttpStatus.OK).body(updateCust);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.class);
        }
    }

}
