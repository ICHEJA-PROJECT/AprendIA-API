package com.icheha.aprendia_api.records.pupilExcerise.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "educando_temas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PupilTopicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_educando", nullable = false)
    private Long pupilId;

    @Column(name = "id_tema", nullable = false)
    private Long topicId;
}
