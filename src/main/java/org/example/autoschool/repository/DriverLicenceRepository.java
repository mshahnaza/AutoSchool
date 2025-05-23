package org.example.autoschool.repository;

import org.example.autoschool.entity.DriverLicence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DriverLicenceRepository extends JpaRepository<DriverLicence, Long> {
    @Query("SELECT dl FROM DriverLicence dl " +
            "JOIN dl.driver s " +
            "JOIN s.studentUser u " +
            "WHERE u.name = :studentName")
    List<DriverLicence> findByStudentName(String studentName);

    @Query("SELECT dl FROM DriverLicence dl " +
            "WHERE dl.driver.id = :id")
    List<DriverLicence> findDtoByStudentId(Long id);

    @Query("SELECT dl FROM DriverLicence dl " +
            "WHERE dl.issueDate = :date")
    List<DriverLicence> findDtoByDate(LocalDate date);

    @Query("SELECT dl FROM DriverLicence dl " +
            "WHERE dl.driver.id = :id AND dl.status = 'ACTIVE'")
    List<DriverLicence> findActiveDtoByStudentId(Long id);

    @Query("SELECT dl FROM DriverLicence dl " +
            "JOIN dl.driver s " +
            "JOIN s.studentUser u " +
            "WHERE u.name = :studentName AND dl.status = 'ACTIVE'")
    List<DriverLicence> findActiveDtoByStudentName(String studentName);
}
