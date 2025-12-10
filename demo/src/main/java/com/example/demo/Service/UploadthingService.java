package com.example.demo.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadthingService {

    private final String API_KEY = "TU_API_KEY";

    public String uploadFile(MultipartFile file) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.uploadthing.com/upload"))
                .header("Authorization", "Bearer " + API_KEY)
                .POST(ofMultipartData(file))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();

        int start = body.indexOf("\"fileUrl\":\"") + 11;
        int end = body.indexOf("\"", start);
        if (start >= 0 && end > start) {
            return body.substring(start, end);
        }
        return null;
    }

    private static HttpRequest.BodyPublisher ofMultipartData(MultipartFile file) throws IOException {
        String boundary = "----WebKitFormBoundary7MA4YWxkTrZu0gW";
        var byteArrays = new ArrayList<byte[]>();
        byteArrays.add(("--" + boundary + "\r\n").getBytes());
        byteArrays.add(("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getOriginalFilename() + "\"\r\n").getBytes());
        byteArrays.add(("Content-Type: " + file.getContentType() + "\r\n\r\n").getBytes());
        byteArrays.add(file.getBytes());
        byteArrays.add(("\r\n--" + boundary + "--").getBytes());

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }
}
