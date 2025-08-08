package net.engineeringdigest.journalApp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique=true)
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef  //Added spring.data.mongodb.auto-index-creation=true in application.properties
    List<JournalEntry> journalEntries=new ArrayList<>();
}
