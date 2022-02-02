package com.codeClan.example.Poker.dataBase.repositories;

import com.codeClan.example.Poker.game.models.GameTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameTableRepository extends JpaRepository<GameTable, Long> {

    Optional<GameTable> findGameTableByGameKey(String gameKey);

}
