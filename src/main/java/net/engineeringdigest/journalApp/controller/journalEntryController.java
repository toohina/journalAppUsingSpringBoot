package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entities.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<JournalEntry> all=journalEntryService.findAll();
        if(all!=null&&!all.isEmpty())return new ResponseEntity<>(all, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> postEntry(@RequestBody JournalEntry journalEntry){
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<JournalEntry> putById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry entry=journalEntryService.findById(myId).orElse(null);
        if(entry!=null){
            entry.setTitle(newEntry.getTitle()!=null&&!newEntry.getTitle().equals("")? newEntry.getTitle() : entry.getTitle());
            entry.setContent(newEntry.getContent()!=null&&!newEntry.getContent().equals("")? newEntry.getContent() : entry.getContent());
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/id/{delId}")
    public ResponseEntity<?> delById(@PathVariable ObjectId delId){
        journalEntryService.deleteById(delId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
