package com.revature.p2_lfg.repository.entities.session;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

@Entity
@Table(schema = "project_two", name = "lfg_tags")
public class Tag {
    @Id
    @GeneratedValue(generator = "lfg_tags_tagid_seq", strategy = GenerationType.AUTO)
    @SequenceGenerator(allocationSize = 1, name = "lfg_tags_tagid_seq", sequenceName = "lfg_tags_tagid_seq")
    int tagid;
    @Column(name = "value")
    String tagvalue;

    @ManyToMany(mappedBy = "tags")
    Set<SessionDetails> taggedsession = new HashSet<>();


}
