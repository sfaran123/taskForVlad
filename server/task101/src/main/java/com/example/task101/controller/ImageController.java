package com.example.task101.controller;

import com.example.task101.intity.Image;
import com.example.task101.repository.ImageRepository;
import com.example.task101.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import java.io.IOException;

@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageRepository imageRepository;

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            imageService.saveImage(fileName, file.getBytes(), file);
            return "File uploaded successfully: " + fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file: " + e.getMessage();
        }
    }

    @DeleteMapping("delete/{id}")
    public HttpStatus deleteImage(@PathVariable("id") Long id) {
        Image image = imageRepository.findById(id).orElse(null);
        assert image != null;
        File fileToDelete = new File(image.getFilePath());
        if (fileToDelete.delete()) {
            imageRepository.deleteById(id);
            return HttpStatus.OK;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR; // Failed to delete file
        }
    }

    @GetMapping("/all")
    public List<String> getAllImagesWithPaths() {
        List<Image> images = imageRepository.findAll();
        List<String> imageUrls = new ArrayList<>();
        for (Image image : images) {
            String imageUrl = constructImageUrl(image.getFilePath());
            imageUrls.add(imageUrl);
        }
        return imageUrls;
    }

    private String constructImageUrl(String filePath) {
        String baseUrl = "http://localhost:8080/images/";
        return baseUrl + filePath;
    }
}
