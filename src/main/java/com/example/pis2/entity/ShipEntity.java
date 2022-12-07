package com.example.pis2.entity;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShipEntity {
    private Long id;
    private String route;
    private Integer portsCount;
    private Integer capacity;
    private Integer duration;
}
