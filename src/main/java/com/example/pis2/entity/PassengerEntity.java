package com.example.pis2.entity;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PassengerEntity {
    private Long id;
    private String firstName;
    private String lastName;
    private Long shipId;
    private List<Long> excursionsIds;
}
