package com.project.Assesment.rest;

import com.project.Assesment.dto.activity.InsertActivityDTO;
import com.project.Assesment.dto.activity.UpdateActivityDTO;
import com.project.Assesment.entity.Activity;
import com.project.Assesment.entity.Customer;
import com.project.Assesment.entity.Variant;
import com.project.Assesment.exceptionHandler.ErrorResponse;
import com.project.Assesment.exceptionHandler.IdNotFoundException;
import com.project.Assesment.service.ActivityService;
import com.project.Assesment.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ActivityRestController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CustomerService customerService;

    // -- CREATE NEW ACTIVITY
    @Operation(summary = "Membuat data Activity baru.", description = "Aktivitas pembelian paket data terbaru.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Added New Activity",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be  Null!",
                    content = @Content)
    })
    @PostMapping("/activity/insert")
    public ResponseEntity<Object> post(@Valid @RequestBody InsertActivityDTO dto){
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Customer customer = customerService.findCustomer(username);
            if (customer == null){
                throw new UsernameNotFoundException("Customer with Username "+username+" not found");
            }
            Long respondId = activityService.insertActivity(dto, username);
            Activity newAct = activityService.getActivity(respondId);
            System.out.println("[newAct : ] "+newAct);

            return ResponseEntity.status(HttpStatus.CREATED).body(newAct);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.class);
        }
    }

    // -- GET SINGLE ACTIVITY BY ACTIVITY ID
    @Operation(summary = "Mendapatkan satu data Activity.", description = "Satu data Activity berdasarkan Activity ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Activity",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Activity.class))}),
            @ApiResponse(responseCode = "404", description = "Activity Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true) Long activityId){

        Activity activity = activityService.getActivity(activityId);
        if (activity == null){
            throw new IdNotFoundException("Activity with Activity ID "+activityId+" not found.");
        }
        UpdateActivityDTO dto = activityService.getUpdateActivity(activityId);
        System.out.println("[UpdateActivityDTO : ] "+dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // -- UPDATE USER STATUS ACTIVITY TO CANCEL
    @Operation(summary = "Mengupdate Activity yang sudah ada.", description = "Response akan mengembalikan informasi data yang baru saja diupdate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Canceled Your Activity.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Variant.class))}),
            @ApiResponse(responseCode = "403", description = "You don't have access.",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be Null!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Activity Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @PutMapping("/activity/{activityId}")
    public ResponseEntity<Object> put(@PathVariable Long activityId,
                                      @Valid @RequestBody UpdateActivityDTO dto){
        try{
            Activity activity = activityService.getActivity(activityId);
            if (activity == null){
                throw new IdNotFoundException("Activity with ID "+activityId+" not found.");
            }
            Long respondId = activityService.updateActivity(dto);
            System.out.println("[respondId : ] "+respondId);

            Activity updateAct = activityService.getActivity(respondId);
            System.out.println("[updateAct : ] "+updateAct);

            return ResponseEntity.status(HttpStatus.OK).body(updateAct);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

//    // -- PAGEABLE RESERVATION
//    @Operation(summary="Mendapatkan data Reservation.", description = "Data akan di respond dalam jumlah 3 per halaman.")
//    @ApiResponses( value = {
//            @ApiResponse(responseCode = "200", description = "View All Reservation Grid",
//                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                            schema = @Schema(implementation = Reservation.class))}),
//            @ApiResponse(responseCode = "404", description = "Page not found",
//                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
//    })
//    @GetMapping("/reservation/page={page}&fullName={fullName}&roomNumber={roomNumber}")
//    public ResponseEntity<Object> getAllReservation(@PathVariable(required = false) Integer page,
//                                                    @PathVariable(required = false) String fullName,
//                                                    @PathVariable(required = false) String roomNumber){
//        Pageable pagination = PageRequest.of(page - 1, 3, Sort.by("customer.firstName"));
//        Page<ReservationGridDTO> grid = reservationService.findAllReserve(fullName, roomNumber, pagination);
//
//        return ResponseEntity.status(HttpStatus.OK).body(grid);
//    }

}
