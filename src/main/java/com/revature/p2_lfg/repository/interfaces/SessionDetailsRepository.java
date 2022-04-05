package com.revature.p2_lfg.repository.interfaces;

import com.revature.p2_lfg.repository.entities.session.SessionDetails;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("sessionDetailsRepository")
public interface SessionDetailsRepository extends CrudRepository<SessionDetails,Integer> {


    @Override
    Optional<SessionDetails> findById(Integer integer);

    @Override
    <S extends SessionDetails> S save(S entity);

    @Query("select s from SessionDetails s where s.game.gameid = ?1")
    List<SessionDetails> findAllByGameId(int gameId);
}
