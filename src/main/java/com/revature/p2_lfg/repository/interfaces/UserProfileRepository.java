package com.revature.p2_lfg.repository.interfaces;

import com.revature.p2_lfg.repository.entities.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userProfileRepository")
public interface UserProfileRepository extends JpaRepository<UserProfile,Integer> {
    @Query("select u from UserProfile u where u.usercredential.userid = ?1")
    Optional<UserProfile> findByUserId(int userId);
}
