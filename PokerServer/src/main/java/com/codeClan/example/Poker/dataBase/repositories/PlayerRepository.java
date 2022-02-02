package com.codeClan.example.Poker.dataBase.repositories;

import com.codeClan.example.Poker.game.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findPlayerByUsernameAndPassword(String username, String password);
}
