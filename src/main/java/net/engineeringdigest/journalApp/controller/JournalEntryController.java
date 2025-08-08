package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entities.JournalEntry;
import net.engineeringdigest.journalApp.entities.User;
import net.engineeringdigest.journalApp.service.JournalEntryService;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    JournalEntryService journalEntryService;
    @Autowired
    UserService userService;


    @GetMapping("{username}")
    public ResponseEntity<?> getAllEntriesForUser(@PathVariable String username){
        User user=userService.getByUserName(username);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null&&!all.isEmpty())return new ResponseEntity<>(all,HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{username}")
    public ResponseEntity<?> createEntryForUser(@RequestBody JournalEntry journalEntry,@PathVariable String username){
        try {
            journalEntryService.saveEntryOfUser(journalEntry,username);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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

    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<?> deleteEntryForUser(@PathVariable String username, @PathVariable ObjectId id){
        journalEntryService.deleteEntryWithIdAndUser(username,id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
