package com.icheha.aprendia_api.assets.assets.data.dtos.response;


import java.util.List;

public interface FindAssetDB {
    Long getId();
    String getName();
    String getDescription();
    String getUrl();
    List<String> getTags();
}
