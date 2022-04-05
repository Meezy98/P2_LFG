//package com.revature.p2_lfg.repository.entities.user;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.experimental.FieldDefaults;
//
//import javax.persistence.*;
//import java.sql.Date;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@Table(schema = "project_two", name = "user_commends")
//@Entity
//public class UserCommends {
//
//    @Id
//    @Column(name = "commendID")
//    @GeneratedValue(generator = "user_commends_commendid_seq", strategy = GenerationType.AUTO)
//    @SequenceGenerator(allocationSize = 1, name = "user_commends_commendid_seq", sequenceName = "user_commends_commendid_seq")
//    int commendID;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "commendingUser", referencedColumnName = "userID")
//    int commendingUser;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "commendedUser",referencedColumnName = "userID")
//    int commendedUser;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "commendTag",referencedColumnName = "commendTagID")
//     private CommendTagTable commendTagTable;
//    @Column
//    String description;
//    @Column
//    Date commendDate;
//
//
//}
