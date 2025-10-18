package com.icheha.aprendia_api.assets.assets.services.impl;

import com.icheha.aprendia_api.assets.assets.services.IVectorService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VectorService implements IVectorService {

    @Override
    public float[] generateVector(String text) {

        return new float[]{1, -2};
    }
}
