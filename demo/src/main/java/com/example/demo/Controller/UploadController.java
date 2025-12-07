package com.example.demo.Controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.UploadthingService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {
    private final UploadthingService uploadthingService;

    public UploadController(UploadthingService uploadThingService) {
        this.uploadthingService = uploadThingService;
    }

    @PostMapping("/media")
    public ResponseEntity<?> uploadMedia(@RequestParam("file") MultipartFile file) {
        try {
            // Llamamos al método del bean inyectado
            String uploadUrl = uploadthingService.uploadFile(file);
            return ResponseEntity.ok(Map.of("url", uploadUrl));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(Map.of("error", e.getMessage()));
        }
    }
}
