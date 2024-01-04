package com.flagship.service;

import com.flagship.dto.request.ImportRequest;
import com.flagship.dto.response.*;

public interface ImportService {
  ImportResponse addImport(ImportRequest importRequest);

  ShipmentResponse getShipment(String product);

  AllImportResponse getAllImport();

  GetUomAndAvailableResponse getProductUomAndAvailable(String product, String shipment);

  WastageDetailsResponse getWastage(String shipment);
}