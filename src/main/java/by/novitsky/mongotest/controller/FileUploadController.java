package by.novitsky.mongotest.controller;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileUploadController {
    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @GetMapping("/hello")
    public String getHello(){
        return "Hello";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile uploadedFile) throws IOException {
        InputStream inputStream = uploadedFile.getInputStream();
        gridFsTemplate.store(inputStream, uploadedFile.getOriginalFilename());
        return "Great success";
    }

    @GetMapping(value = "/get/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getFile(@PathVariable String fileName) throws IOException {
        GridFSFile gridFsFile =  gridFsTemplate.findOne(new Query(Criteria.where("filename").is(fileName)));
        System.out.println(gridFsFile);
        BsonValue id = gridFsFile.getId();

        /*File file = new File("src/main/resources/output/" + fileName);
        try(OutputStream outputStream = new FileOutputStream(file)){
            gridFSBucket.downloadToStream(id,outputStream);
        }*/

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        gridFSBucket.downloadToStream(id, outputStream);
        byte [] result = outputStream.toByteArray();
        //ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);

        return result;
    }

}
