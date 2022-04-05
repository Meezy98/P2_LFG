package com.revature.p2_lfg.repository.interfaces;

import com.revature.p2_lfg.repository.entities.session.Games;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("gamesRepository")
public interface GamesRepository extends JpaRepository<Games, Integer> {

    @Override
    List<Games> findAll();
}
