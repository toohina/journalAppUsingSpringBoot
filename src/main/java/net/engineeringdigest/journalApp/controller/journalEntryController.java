package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entities.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryController {
    Map<Long,JournalEntry> journalEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }

    @PostMapping
    public void postOne(@RequestBody JournalEntry journalEntry){
        journalEntries.put(journalEntry.getId(),journalEntry);
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getById(@PathVariable Long myId){
        return journalEntries.get(myId);
    }

    @PutMapping("/id/{myId}")
    public void putById(@PathVariable Long myId, @RequestBody JournalEntry journalEntry){
        journalEntries.put(myId,journalEntry);
    }

    @DeleteMapping("/id/{delId}")
    public void delById(@PathVariable Long delId){
        journalEntries.remove(delId);
    }

}
