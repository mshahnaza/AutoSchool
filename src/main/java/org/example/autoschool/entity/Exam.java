package org.example.autoschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.autoschool.enums.ExamResult;

import java.time.LocalDate;

@Entity
@Table(name = "exam")
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExamResult result;

    private String remarks;

    private LocalDate expirationAt;

    private LocalDate takenAt;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne
    @JoinColumn(name = "slot_id", referencedColumnName = "id")
    private AvailableSlot availableSlot;
}
