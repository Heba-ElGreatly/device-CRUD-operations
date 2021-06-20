package com.iot.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "SIMCard")
@ToString(exclude = "operation")
@EqualsAndHashCode(exclude = "operation")
public class SIMCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "simNumber")
    private String simNumber;

    @Column(name = "country")
    private String countryName;

    @Column(name = "status")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "operationId")
    private Operation operation;
}
