package com.matcodem.flightmanagement.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateFlightResponse extends BaseResponse {

    private String id;

    public CreateFlightResponse(String message, String id) {
        super(message);
        this.id = id;
    }
}
