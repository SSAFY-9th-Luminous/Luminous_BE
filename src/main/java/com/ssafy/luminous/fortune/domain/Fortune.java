package com.ssafy.luminous.fortune.domain;

import com.ssafy.luminous.constellation.domain.Constellation12;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Fortune {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @Lob
    private String description;

    @OneToOne
    private Constellation12 constellation12;


}
