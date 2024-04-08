package com.stanley.dodospring.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "notes")
public class NoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "note_id_seq")
    @SequenceGenerator(
            name = "note_id_seq",
            sequenceName = "note_id_seq",
            allocationSize = 1
    )
    private Long id;

    @Column(columnDefinition = "varchar(255) default 'Title'")
    private String title = "Title";

    @Column(columnDefinition = "varchar(255) default 'Write something here!'")
    private String body = "Write something here!";

    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "TIMESTAMP default LOCALTIMESTAMP", updatable = false)
    private Date createdAt = new Date();

    private String icon;

    @Column(columnDefinition = "varchar(255) default 'red'")
    private String color = "red";

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private UserEntity user;

}
