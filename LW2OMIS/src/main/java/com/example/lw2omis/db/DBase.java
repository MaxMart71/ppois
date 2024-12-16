package com.example.lw2omis.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBase {
    private String db_url;
//    private Engine engine;
    public DBSession getSession(){
        return null;
    }
}
