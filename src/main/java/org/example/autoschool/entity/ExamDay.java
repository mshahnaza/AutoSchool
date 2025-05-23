package org.example.autoschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.ExamType;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "exam_day")
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExamDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate date;

    @Column(columnDefinition = "integer default 30")
    private Integer maxStudents;
    @Column(columnDefinition = "integer default 0")
    private Integer currentStudents;

    @Enumerated(EnumType.STRING)
    private ExamType examType;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "branch_id", referencedColumnName = "id")
    private Branch branch;

    @OneToMany(mappedBy = "examDay")
    private List<AvailableSlot> availableSlots;
}
