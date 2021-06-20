package com.iot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name ="Operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String operationName;

    @Column(name = "code")
    private String operationCode;

    @OneToMany(mappedBy = "operation", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties
    private Set<SIMCard> simCards;

}
