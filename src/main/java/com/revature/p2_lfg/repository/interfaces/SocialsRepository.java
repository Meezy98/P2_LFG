package com.revature.p2_lfg.repository.interfaces;

import com.revature.p2_lfg.repository.entities.compositeKeys.SocialId;
import com.revature.p2_lfg.repository.entities.user.Socials;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("socialsRepository")
public interface SocialsRepository extends JpaRepository<Socials, SocialId> {

    @Override
    Optional<Socials> findById(SocialId socialId);

    List<Socials> findByUserid(int userid);
}
