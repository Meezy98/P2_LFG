package com.revature.p2_lfg.repository.entities.session;

import com.revature.p2_lfg.repository.entities.compositeKeys.GroupSessionId;
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
@IdClass(GroupSessionId.class)
@Table(schema = "project_two", name = "lfg_group_sessions")
public class Session {
    @Id
    @Column(name = "userid")
    int userid;
    @Id
    @Column(name = "hostid")
    int hostid;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "groupid")
    SessionDetails groupsession;
    @Column(name = "insession")
    boolean insession;
}

