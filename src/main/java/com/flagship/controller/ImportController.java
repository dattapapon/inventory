package com.flagship.controller;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.response.AllImportResponse;
import com.flagship.dto.response.ImportResponse;
import com.flagship.dto.response.ShipmentResponse;
import com.flagship.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/import")
public class ImportController {
  private final ImportService importService;

  @Autowired
  public ImportController(ImportService importService) {
    this.importService = importService;
  }

  @PostMapping(
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ImportResponse> addImport(@Valid @NotNull @RequestBody ImportRequest importRequest) {
    ImportResponse response = importService.addImport(importRequest);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          value = "/shipment",
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<ShipmentResponse> getShipment(@RequestParam(value = "product") String product) {
    ShipmentResponse response = importService.getShipment(product);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping(
          produces = MediaType.APPLICATION_JSON_VALUE
  )
  public ResponseEntity<AllImportResponse> getAllImport() {
    AllImportResponse response = importService.getAllImport();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
