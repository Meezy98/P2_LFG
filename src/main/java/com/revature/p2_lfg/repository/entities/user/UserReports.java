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
//@Entity
//@Table(schema = "project_one", name = "user_reports ")
//
//public class UserReports {
//    @Id
//    @Column(name = "reportID ")
//    @GeneratedValue(generator = "user_reports_reportid_seq", strategy = GenerationType.AUTO)
//    @SequenceGenerator(allocationSize = 1, name = "user_reports_reportid_seq", sequenceName = "user_reports_reportid_seq")
//    int reportID;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "reportingUser", referencedColumnName = "userID")
//    int reportingUser;
//   @OneToOne(cascade = CascadeType.ALL)
//   @JoinColumn(name = "reportTag",referencedColumnName = "userID")
//    private  ReportTagTable reportTagTable;
//    @Column
//    int reportTag;
//    @Column
//    String description;
//    @Column
//    Date incidentDate;
//    @Column
//    boolean reviewStatus;
//}
