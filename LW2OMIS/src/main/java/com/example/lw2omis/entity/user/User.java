package com.example.lw2omis.entity.user;

import com.example.lw2omis.db.DModel;
import com.example.lw2omis.entity.Role;
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
public class User extends DModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String login;

    private String password;

    private String location;

    @Enumerated(EnumType.STRING)
    private Role access_role;

}
