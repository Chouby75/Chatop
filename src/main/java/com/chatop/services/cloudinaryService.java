package com.chatop.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class cloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public Map upload(MultipartFile file) throws IOException {
        try {
            return cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) {
            throw new IOException("Error uploading file to Cloudinary", e);
        }
    }

    public Map upload(MultipartFile file, Map options) throws IOException {
        try {
            return cloudinary.uploader().upload(file.getBytes(), options);
        } catch (IOException e) {
            throw new IOException("Error uploading file to Cloudinary", e);
        }
    }

    // Vous pouvez ajouter d'autres méthodes pour la suppression, la transformation, etc.
}