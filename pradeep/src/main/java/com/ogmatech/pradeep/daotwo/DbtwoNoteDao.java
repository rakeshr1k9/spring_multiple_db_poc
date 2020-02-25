package com.ogmatech.pradeep.daotwo;

import com.ogmatech.pradeep.model.DbtwoNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DbtwoNoteDao extends JpaRepository<DbtwoNote, Integer> {
}
