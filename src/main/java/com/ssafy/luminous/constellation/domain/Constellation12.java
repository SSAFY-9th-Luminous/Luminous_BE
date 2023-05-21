package com.ssafy.luminous.constellation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Constellation12 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @OneToOne
    @JoinColumn(nullable = false)
    private ConstellationDetail constellationDetail;

}
