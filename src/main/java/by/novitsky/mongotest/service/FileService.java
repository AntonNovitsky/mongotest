package by.novitsky.mongotest.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileService {
    private GridFsTemplate gridFsTemplate;
    private GridFSBucket gridFSBucket;

    @Autowired
    public FileService(GridFsTemplate gridFsTemplate, GridFSBucket gridFSBucket) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFSBucket = gridFSBucket;
    }

    public void uploadImage(MultipartFile uploadedFile) throws IOException {
        GridFSFindIterable gridFSFindIterable = gridFsTemplate.find(new Query(Criteria.where("filename").is(uploadedFile.getOriginalFilename())));
        if (gridFSFindIterable.iterator().hasNext()){
            throw new RuntimeException("File already exists");
        }
        try(InputStream inputStream = uploadedFile.getInputStream()){
            gridFsTemplate.store(inputStream, uploadedFile.getOriginalFilename());
        }

    }

    public byte [] getImage(String fileName) throws IOException {
        GridFSFile gridFsFile =  gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
        BsonValue id = gridFsFile.getId();

        byte [] result;
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
            gridFSBucket.downloadToStream(id, outputStream);
            result = outputStream.toByteArray();
        }

        return result;
    }

}
