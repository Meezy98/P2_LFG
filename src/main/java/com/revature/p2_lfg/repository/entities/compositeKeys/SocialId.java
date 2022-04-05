package com.revature.p2_lfg.repository.entities.compositeKeys;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SocialId implements Serializable {
    int userid;
    int gameid;
}
