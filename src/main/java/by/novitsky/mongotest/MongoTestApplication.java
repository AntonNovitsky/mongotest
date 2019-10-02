package by.novitsky.mongotest;

import by.novitsky.mongotest.dao.UserRepository;
import by.novitsky.mongotest.entity.User;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import sun.tools.jar.CommandLine;

import java.io.*;
import java.util.Scanner;

@SpringBootApplication
public class MongoTestApplication /*implements CommandLineRunner*/ {

    @Autowired
    private UserRepository repository;

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFSBucket gridFSBucket;

    public static void main(String[] args) {
        SpringApplication.run(MongoTestApplication.class, args);
    }

    /*@Override
    public void run(String... args) throws Exception{
        repository.deleteAll();
        repository.save(new User("John","Smith"));
        repository.save(new User("John", "Other"));
        System.out.println(repository.findByFirstName("John"));
        User user = repository.findByFirstName("John").get(0);
        user.setLastName("NotSmith");
        repository.save(user);
        System.out.println(repository.findByFirstName("John"));

        try(InputStream inputStream = new FileInputStream("src/main/resources/test.png")){
            gridFsTemplate.store(inputStream, "test.png");
        }

        GridFSFile gridFsFile =  gridFsTemplate.findOne(new Query(Criteria.where("filename").is("test.png")));
        System.out.println(gridFsFile);
        BsonValue id = gridFsFile.getId();
        File file = new File("src/main/resources/output/test.png");
        file.delete();
        try(OutputStream outputStream = new FileOutputStream(file)){
            gridFSBucket.downloadToStream(id,outputStream);
        }

        gridFSBucket.delete(id);
        gridFsFile =  gridFsTemplate.findOne(new Query(Criteria.where("filename").is("test.png")));
        System.out.println(gridFsFile);

    }*/
}
