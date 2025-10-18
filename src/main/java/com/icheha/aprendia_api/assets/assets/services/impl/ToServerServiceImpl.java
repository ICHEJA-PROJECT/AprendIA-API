package com.icheha.aprendia_api.assets.assets.services.impl;

import com.icheha.aprendia_api.assets.assets.data.dtos.response.UploadResponseDto;
import com.icheha.aprendia_api.assets.assets.services.IToServerService;
import com.icheha.aprendia_api.assets.assets.services.dtos.CloudinaryApiResponse;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ToServerServiceImpl implements IToServerService {
    private final String apiUrl = "http://localhost:8000/api/cloudinary";
    private final String folder = "pruebasAlex";

    @Override
    public UploadResponseDto upload(MultipartFile file, String name) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("fileName", name);
        body.add("folder", folder);

        try {
            body.add("file", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo " + name, e);
        }

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);
        ResponseEntity<CloudinaryApiResponse> response;
        try {
            response = new RestTemplate().postForEntity(apiUrl+"/upload", request, CloudinaryApiResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el archivo " + name + ": " + e.getMessage(), e);
        }

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            // Extraemos los datos del cuerpo de la respuesta
            CloudinaryApiResponse apiResponse = response.getBody();
            String publicId = apiResponse.getData().getPublicId();
            String secureUrl = apiResponse.getData().getSecureUrl();

            // Creamos y retornamos nuestro objeto final
            return new UploadResponseDto(publicId, secureUrl);
        } else {
            System.err.println("Error en la llamada: " + response.getStatusCode());
            // En caso de error, podrías retornar null o lanzar una excepción
            throw new RuntimeException("La API devolvió un código de error: " + response.getStatusCode());
        }
    }

    @Override
    public void delete(String publicId) {
        if (publicId.isEmpty()) {
            return;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response;

        String url = apiUrl + "/{id}";

        try {
            response = new RestTemplate().exchange(
                    url,
                    HttpMethod.DELETE,
                    requestEntity,
                    String.class,
                    publicId
            );

        } catch (Exception e) {
            System.err.println("Error al intentar eliminar " + publicId + ": " + e.getMessage());
            throw new RuntimeException("Error al conectar con la API de borrado: " + e.getMessage(), e);
        }

        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Respuesta de la API (borrado): " + response.getBody());
        } else {
            System.err.println("Error en la llamada DELETE: " + response.getStatusCode());
            throw new RuntimeException("La API devolvió un código de error: " + response.getStatusCode());
        }
    }
}
