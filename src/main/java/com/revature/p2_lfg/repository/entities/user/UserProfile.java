package com.revature.p2_lfg.repository.entities.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(schema = "project_two", name = "lfg_user_profile")
public class UserProfile {
    @Id
    @Column(name = "columnid")
    @GeneratedValue(generator = "lfg_user_profile_columnid_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(allocationSize = 1, name = "lfg_user_profile_columnid_seq",
            sequenceName = "lfg_user_profile_columnid_seq")
    private int columnID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    UserCredential usercredential;
    @Column
    String firstname;
    @Column
    String lastname;
    @Column
    String email;


}
