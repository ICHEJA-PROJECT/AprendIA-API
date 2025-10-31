package com.icheha.aprendia_api.assets.assets.domain.exceptions;

import com.icheha.aprendia_api.auth.domain.exceptions.DomainException;

public class AssetNotFoundException extends DomainException {
    public AssetNotFoundException(String message) {
        super(message);
    }

    public static AssetNotFoundException byId(String id) {
        return new AssetNotFoundException("Asset con ID " + id + " no encontrado");
    }
}
