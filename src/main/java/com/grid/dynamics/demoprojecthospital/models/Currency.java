package com.grid.dynamics.demoprojecthospital.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * This class created for API currency. This class receive JSON format ,parse and provide needed fields with up-to-date data.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Currency implements Serializable {
    private int codCurrency;
    private String nameCurrency;
    private double rate;
    private String shortNameCurrency;
    private String exchangeDate;

}
