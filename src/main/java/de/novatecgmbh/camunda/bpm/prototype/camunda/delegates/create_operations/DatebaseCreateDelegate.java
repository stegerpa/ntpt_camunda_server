package de.novatecgmbh.camunda.bpm.prototype.camunda.delegates.create_operations;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service("createDatabaseAdapter")
public class DatebaseCreateDelegate implements JavaDelegate {

    public void execute(DelegateExecution execution) throws Exception {
        String prefix = execution.getVariable("git_project")
                + "_"
                + execution.getVariable("branch_name");

        String dbUrl = (String) execution.getVariable("db_url"); // MUST be in format http://host:port

        // Split URL into host + port
        Pattern pattern = Pattern.compile("(https?://)([^:^/]*)(:\\d*)?(.*)?");
        Matcher matcher = pattern.matcher(dbUrl);
        matcher.find();

        String domain   = matcher.group(2);
        int port     = Integer.parseInt(matcher.group(3).replaceAll("\\D+",""));

        // Connect to MongoDB Driver
        MongoClient mongoClient = new MongoClient(domain, port);
        MongoDatabase db = mongoClient.getDatabase(prefix);

        // Create a sample document to actually create the new database
        Document doc = new Document("name", "MongoDB")
                .append("type", "database");
        MongoCollection<Document> collection = db.getCollection("doc");
        collection.insertOne(doc);

        System.out.println("###\nDB created\n###");
    }

}