package com.example.lw2omis.entity.land;


import com.example.lw2omis.db.DModel;
import com.example.lw2omis.entity.TypeOfPlant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Land extends DModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer square;

    @Enumerated(EnumType.STRING)
    private TypeOfPlant type;

}

