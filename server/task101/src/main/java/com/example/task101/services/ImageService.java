package com.example.task101.services;

import com.example.task101.intity.Image;
import com.example.task101.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(String fileName, byte[] data, MultipartFile file) throws IOException {
        jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO images (name, data) VALUES (?, ?)";
        jdbcTemplate.update(sql, fileName, data);
        file.transferTo(new File("./images" + file.getOriginalFilename()));
    }
}
