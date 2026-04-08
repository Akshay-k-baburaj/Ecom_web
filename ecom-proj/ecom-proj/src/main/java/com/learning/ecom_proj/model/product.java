package com.learning.ecom_proj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String brand;
    private BigDecimal price;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;
    private boolean available;
    private int quantity;

    private String imageName;
    private String imageType;

    @Lob
    private byte[] imageDate;
}
