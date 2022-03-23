package com.grid.dynamics.demoprojecthospital.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@JsonDeserialize(builder = Room.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode
public class Room {
    /**
     * The RoomDTO class represents the room DTO.
     */
    private Long roomId;
    private Long hospitalId;
    private int numberRoom;
    private int numberFloor;
    private String description;
    private boolean bookingStatus = false;

    @Setter
    @Getter
    @JsonPOJOBuilder
    public static class Builder {
        private Long roomId;
        private Long hospitalId;
        private int numberRoom;
        private int numberFloor;
        private String description;
        private boolean bookingStatus = false;

        public Room build() {
            return new Room(roomId, hospitalId, numberRoom, numberFloor, description, bookingStatus);
        }
    }
}
