package com.github.kindrat.programmerwars.tanks.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.kindrat.programmerwars.tanks.server.persistence.entity.Unit;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {
}
