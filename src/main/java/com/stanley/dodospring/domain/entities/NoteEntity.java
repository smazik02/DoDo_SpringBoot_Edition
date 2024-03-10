package com.stanley.dodospring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    private Long id;

    @Column(columnDefinition = "varchar(255) default 'Title'")
    private String title;

    @Column(columnDefinition = "varchar(255) default 'Write something here!'")
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP default LOCALTIMESTAMP")
    private Date createdAt;

    private String icon;

    @Column(columnDefinition = "varchar(255) default 'red'")
    private String color;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private UserEntity user;

}
