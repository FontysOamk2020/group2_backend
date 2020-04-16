package com.hotspotted.server.repository;

import com.hotspotted.server.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    @Query("FROM Student WHERE sub = :sub")
    Optional<Student> findBySub(@Param("sub") String sub);

    @Query("FROM Student WHERE (:name is null or name = :name) AND (:nickname is null or nickname = :nickname)")
    List<Student> findBySearchParams(@Param("name") String name, @Param("nickname") String nickname);
}
