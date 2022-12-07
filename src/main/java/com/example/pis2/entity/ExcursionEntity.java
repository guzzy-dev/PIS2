package com.example.pis2.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ExcursionEntity {
    private Long id;
    private String title;
    private Float price;
}
