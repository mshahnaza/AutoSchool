package org.example.autoschool.repository;

import org.example.autoschool.entity.AvailableSlot;
import org.example.autoschool.entity.DriverLicence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverLicenceRepository extends JpaRepository<DriverLicence, Long> {
}
