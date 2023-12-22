package com.flagship.service;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.response.AllImportResponse;
import com.flagship.dto.response.ImportResponse;
import com.flagship.dto.response.ShipmentResponse;

public interface ImportService {
  ImportResponse addImport(ImportRequest importRequest);

  ShipmentResponse getShipment(String product);

  AllImportResponse getAllImport();
}