package com.icheha.aprendia_api.users.person.services.impl;

import com.icheha.aprendia_api.assets.assets.services.dtos.CloudinaryApiResponse;
import com.icheha.aprendia_api.users.person.services.IImageUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ImageUploadServiceImpl implements IImageUploadService {
    
    private final RestTemplate restTemplate;
    private final String cloudinaryServiceUrl;
    
    public ImageUploadServiceImpl(RestTemplate restTemplate,
                                 @Value("${app.cloudinary.service-url:http://localhost:8000/api/cloudinary}") String cloudinaryServiceUrl) {
        this.restTemplate = restTemplate;
        this.cloudinaryServiceUrl = cloudinaryServiceUrl;
    }
    
    @Override
    public String uploadImage(byte[] fileBytes, String fileName, String folder) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("file", new ByteArrayResource(fileBytes) {
                @Override
                public String getFilename() {
                    return fileName;
                }
            });
            body.add("fileName", fileName);
            body.add("folder", folder != null ? folder : "profiles-images");
            
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
            
            String url = cloudinaryServiceUrl + "/upload";
            ResponseEntity<CloudinaryApiResponse> response = restTemplate.postForEntity(
                    url, requestEntity, CloudinaryApiResponse.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                CloudinaryApiResponse cloudinaryResponse = response.getBody();
                if (cloudinaryResponse.getData() != null && cloudinaryResponse.getData().getSecureUrl() != null) {
                    return cloudinaryResponse.getData().getSecureUrl();
                }
            }
            
            throw new RuntimeException("Error al subir imagen a Cloudinary");
        } catch (Exception e) {
            throw new RuntimeException("Error al subir imagen: " + e.getMessage(), e);
        }
    }
}

