package com.ssafy.luminous.constellation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ConstellationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String contentsName;

    @Column(nullable = false)
    private String ScientificName;

    @Column(nullable = false)
    private String engName;

    private LocalDateTime meridianDateTime;

    @Column(nullable = false)
    private Integer amountOfStars;

    @Column(nullable = false)
    private String size;

    @Column(nullable = false)
    private String location;

    @Lob
    @Column(nullable = false)
    private String contentsDescription;

    private String img;

}
