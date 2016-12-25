package com.github.kindrat.programmerwars.server.persistence.repository;

import com.github.kindrat.programmerwars.server.persistence.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
