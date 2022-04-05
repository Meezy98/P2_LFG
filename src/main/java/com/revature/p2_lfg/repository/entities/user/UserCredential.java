package com.revature.p2_lfg.repository.entities.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "lfg_user_creds", schema = "project_two")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCredential {
    @Id
    @Column(name = "userid")
    @GeneratedValue(generator = "lfg_user_creds_user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_creds_user_id_seq", sequenceName = "lfg_user_creds_user_id_seq")
    int userid;
    @Column(name = "userlogin")
    String username;
    @Column(name = "userpass")
    String password;

}
