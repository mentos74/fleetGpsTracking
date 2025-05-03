package com.lacakio.fleetgpstracking.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Builder
@Data
public class ApiErrorResponse {
    private int status;
    private List<Map<String, Object>> errors;
    private String path;
    private String timestamp;


}
