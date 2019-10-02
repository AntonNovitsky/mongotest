package by.novitsky.mongotest.controller;

import by.novitsky.mongotest.service.FileService;
import com.mongodb.client.gridfs.GridFSBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileUploadController {

    private GridFsTemplate gridFsTemplate;
    private GridFSBucket gridFSBucket;
    private FileService fileService;

    @Autowired
    public FileUploadController(GridFsTemplate gridFsTemplate, GridFSBucket gridFSBucket, FileService fileService) {
        this.gridFsTemplate = gridFsTemplate;
        this.gridFSBucket = gridFSBucket;
        this.fileService = fileService;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam MultipartFile uploadedFile) throws IOException {
        fileService.uploadImage(uploadedFile);
        return "Great success";
    }

    @GetMapping(value = "/get/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getFile(@PathVariable String fileName) throws IOException {
        return fileService.getImage(fileName);
    }

}
