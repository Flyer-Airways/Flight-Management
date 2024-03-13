package com.matcodem.flightmanagement.application.commands;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
public abstract class BaseCommand extends Message {

    protected BaseCommand(String id) {
        super(id);
    }
}
