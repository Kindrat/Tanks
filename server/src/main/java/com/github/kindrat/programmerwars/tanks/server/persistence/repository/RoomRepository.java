package com.github.kindrat.programmerwars.tanks.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.kindrat.programmerwars.tanks.server.persistence.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
