package by.novitsky.mongotest.controller;

import by.novitsky.mongotest.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
public class FileUploadController {
    private final FileService fileService;

    @Autowired
    public FileUploadController(FileService fileService) {
        this.fileService = fileService;
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
