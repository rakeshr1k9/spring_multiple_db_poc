package com.ogmatech.pradeep.daoone;

import com.ogmatech.pradeep.model.DboneNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DboneNoteDao extends JpaRepository<DboneNote, Integer> {
}
