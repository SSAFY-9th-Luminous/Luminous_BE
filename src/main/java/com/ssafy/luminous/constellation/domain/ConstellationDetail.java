package com.ssafy.luminous.constellation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
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

    @Column(nullable = false)
    private Date meridianDate;

    @Column(nullable = false)
    private Integer amountOfStars;

    @Lob
    @Column(nullable = false)
    private String contentsDescription;

    private String img;

    @OneToOne
    Constellation constellation;

}
