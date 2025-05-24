package org.example.autoschool.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.autoschool.enums.Category;
import org.example.autoschool.enums.Status;

import java.time.LocalDate;

@Entity
@Table(name = "driver_licence")
@Setter
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class DriverLicence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String licenceNumber;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate issueDate;
    private LocalDate expiryDate;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Student driver;

    @PrePersist
    private void prePersist() {
        issueDate = LocalDate.now();
        expiryDate = issueDate.plusYears(10);

        if (status == null)
            status = Status.ACTIVE;
    }
}
