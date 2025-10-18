package com.icheha.aprendia_api.assets.assets.services;

import com.icheha.aprendia_api.assets.assets.data.dtos.response.UploadResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface IToServerService {

    UploadResponseDto upload(MultipartFile file, String name);
    void delete(String publicId);
}
