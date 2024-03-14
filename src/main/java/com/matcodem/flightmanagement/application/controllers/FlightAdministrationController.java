package com.matcodem.flightmanagement.application.controllers;

import com.matcodem.flightmanagement.application.commands.CancelFlightCommand;
import com.matcodem.flightmanagement.application.commands.CreateFlightCommand;
import com.matcodem.flightmanagement.application.dto.BaseResponse;
import com.matcodem.flightmanagement.application.dto.CreateFlightResponse;
import com.matcodem.flightmanagement.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/flights")
public class FlightAdministrationController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> createFlight(@RequestBody CreateFlightCommand command) {
        String designatorCode = UUID.randomUUID().toString();
        command.setId(designatorCode);

        commandDispatcher.send(command);
        return ResponseEntity.status(CREATED).body(new CreateFlightResponse("Flight creation request completed successfully.", designatorCode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> cancelFlight(@PathVariable("id") String id) {
        commandDispatcher.send(new CancelFlightCommand(id));
        return ResponseEntity.ok(new BaseResponse("Flight has been canceled successfully."));
    }
}
