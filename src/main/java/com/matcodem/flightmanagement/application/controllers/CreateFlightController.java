package com.matcodem.flightmanagement.application.controllers;

import com.matcodem.flightmanagement.application.commands.CreateFlightCommand;
import com.matcodem.flightmanagement.application.dto.BaseResponse;
import com.matcodem.flightmanagement.application.dto.CreateFlightResponse;
import com.matcodem.flightmanagement.infrastructure.CommandDispatcher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

import static java.util.UUID.randomUUID;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/flights")
public class CreateFlightController {

    private final CommandDispatcher commandDispatcher;

    @PostMapping
    public ResponseEntity<BaseResponse> createFlight(@RequestBody CreateFlightCommand command) {
        var designatorCode = randomUUID().toString();
        command.setId(designatorCode);
        try {
            commandDispatcher.send(command);
            return new ResponseEntity<>(new CreateFlightResponse("Flight creation request completed successfully.", designatorCode), CREATED);
        } catch (IllegalStateException e) {
            log.warn(MessageFormat.format("Client made a bad request - {0}.", e.toString()), e);
            return new ResponseEntity<>(new BaseResponse(e.toString()), BAD_REQUEST);
        } catch (Exception e) {
            String safeErrorMessage = MessageFormat.format( "Error while processing request to create a new flight for designator code {0}!", designatorCode);
            log.error(safeErrorMessage, e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), INTERNAL_SERVER_ERROR);
        }
    }
}
