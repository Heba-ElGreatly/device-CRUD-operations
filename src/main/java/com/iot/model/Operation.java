package com.iot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name ="Operation")
@NoArgsConstructor
@AllArgsConstructor
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
