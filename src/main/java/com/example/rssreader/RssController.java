package com.example.rssreader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/rss")
public class RssController {

    @GetMapping("/scifi")
    public ResponseEntity<String> getScifi() {
        return readFileContent("static/scifi.txt");
    }

    @GetMapping("/romantic")
    public ResponseEntity<String> getRomantic() {
        return readFileContent("static/romantic.txt");
    }

    @GetMapping("/historic")
    public ResponseEntity<String> getHistoric() {
        return readFileContent("static/historic.txt");
    }

    private ResponseEntity<String> readFileContent(String fileName) {
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            byte[] data = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String content = new String(data, StandardCharsets.UTF_8);
            return new ResponseEntity<>(content, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
    }
}
