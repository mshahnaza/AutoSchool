package org.example.autoschool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Table(name = "available_slot")
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime time;

    private Integer maxStudentNumber;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "exam_day_id", referencedColumnName = "id")
    private ExamDay examDay;

    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    private Instructor instructor;
}
