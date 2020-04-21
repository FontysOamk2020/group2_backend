package com.hotspotted.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String upload(MultipartFile photo);
}