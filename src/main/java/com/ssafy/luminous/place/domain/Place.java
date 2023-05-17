package com.ssafy.luminous.place.domain;

import com.ssafy.luminous.member.domain.Member;
import lombok.*;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Date;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String placeName;

    @Lob
    private String placeDescription;

    @Column(nullable = false)
    private Date visitedDate;

    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date createdDate;
    @Column(columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date lastModifiedDate;

    private Blob img;

    @Column(nullable = false)
    private Double latitude;
    @Column(nullable = false)
    private Double longitude;

    private String address;

    private Double rate = (double) 0;

    private Integer hit = 0;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


}
