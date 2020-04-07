package com.hotspotted.server.repository;


import com.hotspotted.server.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("select s from Student s where s.sub = :sub")
    Optional<Student> findBySub(@Param("sub") String sub);
}
