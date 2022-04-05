package com.revature.p2_lfg.repository.entities.session;

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
@Table(schema = "project_two", name = "lfg_games")
public class Games {
    @Id
    @Column(name = "gameid")
    @GeneratedValue(generator = "auto_increment", strategy = GenerationType.IDENTITY)
    @SequenceGenerator(allocationSize = 1, name = "lfg_games_gameid_seq", sequenceName = "lfg_games_gameid_seq")
    int gameid;
    @Column(name = "platformkey")
    int platformkey;
    @Column
    String gametitle;
    @Column
    String imglink;

}
