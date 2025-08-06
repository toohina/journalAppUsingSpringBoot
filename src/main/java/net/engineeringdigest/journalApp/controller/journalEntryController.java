package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entities.JournalEntry;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class journalEntryController {

    @Autowired
    JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalEntryService.findAll();
    }

    @PostMapping
    public void postEntry(@RequestBody JournalEntry journalEntry){
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable ObjectId myId){
        return journalEntryService.findById(myId).orElse(null);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry putById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry){
        JournalEntry entry=journalEntryService.findById(myId).orElse(null);
        if(entry!=null){
            entry.setTitle(newEntry.getTitle()!=null&&!newEntry.getTitle().equals("")? newEntry.getTitle() : entry.getTitle());
            entry.setContent(newEntry.getContent()!=null&&!newEntry.getContent().equals("")? newEntry.getContent() : entry.getContent());
        }
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @DeleteMapping("/id/{delId}")
    public void delById(@PathVariable ObjectId delId){
        journalEntryService.deleteById(delId);
    }

}
