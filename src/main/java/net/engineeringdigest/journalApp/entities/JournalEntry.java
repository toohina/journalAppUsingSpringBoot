package net.engineeringdigest.journalApp.entities;
//POJO: Plain Old Java Object
//will be mapped to document in jounral_entries

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection="journal_entries")
@Data
//@Getter
//@Setter
public class JournalEntry {

    @Id
    ObjectId id;
    String title;
    String content;
    LocalDateTime date;

}
