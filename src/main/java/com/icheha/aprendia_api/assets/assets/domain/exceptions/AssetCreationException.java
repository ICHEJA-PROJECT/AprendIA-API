package com.icheha.aprendia_api.assets.assets.domain.exceptions;

import com.icheha.aprendia_api.auth.domain.exceptions.DomainException;

public class AssetCreationException extends DomainException {
    public AssetCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
