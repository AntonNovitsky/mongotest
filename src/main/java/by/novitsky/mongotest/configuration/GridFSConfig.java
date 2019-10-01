package by.novitsky.mongotest.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

@Configuration
public class GridFSConfig {

    MongoDbFactory mongoDBFactory;
    MappingMongoConverter mappingMongoConverter;
    MongoClient mongoClient;

    @Autowired
    public GridFSConfig(MongoDbFactory mongoDBFactory, MappingMongoConverter mappingMongoConverter, MongoClient mongoClient) {
        this.mongoDBFactory = mongoDBFactory;
        this.mappingMongoConverter = mappingMongoConverter;
        this.mongoClient = mongoClient;
    }

    @Bean
    public GridFsTemplate gridFsTemplate(){
        return new GridFsTemplate(mongoDBFactory, mappingMongoConverter);
    }

    @Bean
    public GridFSBucket gridFSBucket(){
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        GridFSBucket gridFSBucket = GridFSBuckets.create(mongoDatabase);
        return gridFSBucket;
    }

}
