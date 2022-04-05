package com.revature.p2_lfg.repository.interfaces;

import com.revature.p2_lfg.repository.entities.user.UserCredential;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("loginRepository")
public interface LoginRepository extends JpaRepository<UserCredential, Integer> {

   @Override
   <S extends UserCredential> S save(S entity);

   @Override
   Optional<UserCredential> findById(Integer integer);

   UserCredential findByUsername(String username);

   @Query("select u.userid from UserCredential u where u.username = ?1")
   int findIdByUsername(String memberUsername);

   @Query("select u from UserCredential u where u.username = ?1 and u.password = ?2")
   UserCredential findByUsernameAndPassword(String username, String password);
}
