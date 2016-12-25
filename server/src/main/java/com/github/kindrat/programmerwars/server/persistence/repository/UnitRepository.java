package com.github.kindrat.programmerwars.server.persistence.repository;

import com.github.kindrat.programmerwars.server.persistence.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
