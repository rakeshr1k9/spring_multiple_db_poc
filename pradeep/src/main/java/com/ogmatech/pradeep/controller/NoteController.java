package com.ogmatech.pradeep.controller;

import com.ogmatech.pradeep.daoone.DboneNoteDao;
import com.ogmatech.pradeep.daotwo.DbtwoNoteDao;
import com.ogmatech.pradeep.model.DboneNote;
import com.ogmatech.pradeep.model.DbtwoNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private DboneNoteDao dboneNoteDao;

    @Autowired
    private DbtwoNoteDao dbtwoNoteDao;

    @Autowired
    @Qualifier("dboneEntityManagerFactory")
    private EntityManager dboneEm;

    @Autowired
    @Qualifier("dbtwoEntityManagerFactory")
    private EntityManager dbtwoEm;

    @GetMapping("/showdbonenotes")
    public ResponseEntity<?> getDboneNotes() {

        List<DboneNote> dboneNoteList = null;
       // dboneNoteList = dboneNoteDao.findAll();

        try {
            String sql = "select n from DboneNote n";
            Query query = dboneEm.createQuery(sql);
            dboneNoteList = (List<DboneNote>)query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(dboneNoteList);
    }

    @GetMapping("/showdbtwonotes")
    public ResponseEntity<?> getDbtwoNotes() {

        List<DbtwoNote> dbtwoNoteList = null;
        // dbtwoNoteList = dbtwoNoteDao.findAll();

        try {
            String sql = "select n from DbtwoNote n";
            Query query = dbtwoEm.createQuery(sql);
            dbtwoNoteList = (List<DbtwoNote>)query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(dbtwoNoteList);
    }
}
