package com.stanley.dodospring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @Column(nullable = false)
    private String title = "Title";

    @Column(nullable = false)
    private String body = "Write something here!";

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private String icon;

    @Column(nullable = false)
    private String color = "red";

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private UserEntity user;

}
