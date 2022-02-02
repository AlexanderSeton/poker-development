package com.codeClan.example.Poker.dataBase.repositories;

import com.codeClan.example.Poker.game.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

}
