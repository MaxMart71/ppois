package com.example.lw2omis.entity.plant;

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
public class Plant extends DModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean registered;

    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    private TypeOfPlant type;

    private String description;

    private Integer sunny_hours;

    private Integer watering_time;

    private Integer todays_sunny_hours;

    private LocalDate last_watering_day;

}
