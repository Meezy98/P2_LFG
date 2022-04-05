//package com.revature.p2_lfg.repository.entities.user;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//import javax.persistence.*;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Table(schema = "project_two", name = "user_score")
//@Entity
//public class UserScore {
//    @Id
//    @Column(name = "scoreID")
//    int scoreID;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "userID", referencedColumnName = "userID")
//    int userID;
//    int rating;
//}
