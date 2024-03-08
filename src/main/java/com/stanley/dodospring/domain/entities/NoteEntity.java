package com.stanley.dodospring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notes")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    private Long id;

    @Column(columnDefinition = "Title")
    private String title;

    @Column(columnDefinition = "Write something here!")
    private String body;

    @Temporal(TemporalType.DATE)
    private Date createdAt;

    private String icon;

    private String color;

    private UserEntity user;

}
