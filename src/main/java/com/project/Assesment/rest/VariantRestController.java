package com.project.Assesment.rest;

import com.project.Assesment.dto.account.AccountRegisterDTO;
import com.project.Assesment.dto.customer.UpdateCustomerDTO;
import com.project.Assesment.dto.variant.InsertVariantDTO;
import com.project.Assesment.dto.variant.UpdateVariantDTO;
import com.project.Assesment.dto.variant.VariantGridDTO;
import com.project.Assesment.entity.Customer;
import com.project.Assesment.entity.Variant;
import com.project.Assesment.exceptionHandler.DependencyException;
import com.project.Assesment.exceptionHandler.ErrorResponse;
import com.project.Assesment.exceptionHandler.IdNotFoundException;
import com.project.Assesment.service.VariantService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VariantRestController {

    @Autowired
    private VariantService variantService;

    // -- CREATE NEW VARIANT
    @Operation(summary = "Membuat data Variasi Paket baru.", description = "Data Variasi Paket baru.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Added New Variant",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Variant.class))}),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be  Null!",
                    content = @Content)
    })
    @PostMapping("/variant/insert")
    public ResponseEntity<Object> post(@Valid @RequestBody InsertVariantDTO dto){
        try{
            Long respondId = variantService.insertVariant(dto);
            Variant newVar = variantService.getVariant(respondId);
            return ResponseEntity.status(HttpStatus.CREATED).body(newVar);
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorResponse.class);
        }
    }

    // -- GET ALL PAGE VARIANT
    @Operation(summary = "Menampilkan seluruh data Variasi Paket.", description = "Data akan di respond dalam jumlah 2 per halaman.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Variant Page.",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Variant.class))}),
            @ApiResponse(responseCode = "404", description = "Page not found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be  Null!",
                    content = @Content)
    })
    @GetMapping("/variant/page")
    public ResponseEntity<Object> getAllVariants(@RequestParam(defaultValue = "1") Integer page){
        Pageable pageable = PageRequest.of(page - 1, 2, Sort.by("variantId"));
        Page<Variant> variantPage = variantService.findAll(pageable);
        List<Variant> variants = variantPage.stream().toList();
        return new ResponseEntity<>(variants, HttpStatus.OK);
    }

    // -- GET SINGLE VARIANT BY VARIANT ID
    @Operation(summary = "Mendapatkan satu data Variant.", description = "Satu data Variant berdasarkan Variant ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Variant",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Variant.class))}),
            @ApiResponse(responseCode = "404", description = "Variant Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @GetMapping("/variant/{variantId}")
    public ResponseEntity<Object> getUpdate(@PathVariable(required = true) Long variantId){
        Variant variant = variantService.findVariant(variantId);
        if (variant == null){
            throw new IdNotFoundException("Variant with variant ID "+variantId+" not found.");
        }
        UpdateVariantDTO dto = variantService.getUpdateVariant(variantId);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    // -- UPDATE VARIANT BY ID
    @Operation(summary = "Mengupdate Room yang sudah ada.", description = "Response akan mengembalikan informasi data yang baru saja diupdate.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Updated Room",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Variant.class))}),
            @ApiResponse(responseCode = "403", description = "You don't have access.",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "406", description = "Field Can't be Null!",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Variant Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
    })
    @PutMapping("/variant/{variantId}")
    public ResponseEntity<Object> put(@PathVariable Long variantId,
                                      @Valid @RequestBody UpdateVariantDTO dto){
        try{
            Variant find = variantService.findVariant(variantId);
            if (find == null){
                throw new IdNotFoundException("Variant with ID "+variantId+" not found.");
            }
            Long respondId = variantService.updateVariant(dto);
            Variant updateVar = variantService.findVariant(respondId);

            return ResponseEntity.status(HttpStatus.OK).body(updateVar);
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There is a run-time error on the server.");
        }
    }

    // -- DELETE VARIANT BY ID
    @Operation(summary = "Menghapus Variant berdasarkan Variant ID.", description = "Response akan mengembalikan informasi berupa ID dari data yang barus saja dihapus.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Deleted Room",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Variant.class))}),
            @ApiResponse(responseCode = "403", description = "You don't have access.",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "404", description = "Room Not Found",
                    content = {@Content(schema = @Schema(implementation = ErrorResponse.class))})
    })
    @DeleteMapping("/variant/{variantId}")
    public ResponseEntity<Object> delete(@PathVariable(required = true) Long variantId){
        Variant findVariant = variantService.findVariant(variantId);
        if (findVariant == null){
            throw new IdNotFoundException("Variant with ID "+variantId+" not found");
        } else {
            Integer dependent = variantService.dependentVariant(variantId);
            if (dependent > 0){
                throw new DependencyException("Failed to Delete Variant with ID "+variantId+", because this Variant has any Activity or History.");
            }
            variantService.deleteVariant(variantId);
            return ResponseEntity.status(HttpStatus.OK).body("Successful Deleted Variant with ID "+variantId+" !");
        }
    }

}
