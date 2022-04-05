package com.revature.p2_lfg.repository.entities.compositeKeys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupSessionId implements Serializable {
    private int userid;
    private int hostid;
}
