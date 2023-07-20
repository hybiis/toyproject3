package com.example.modulecore.image;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ImageUpload {
	private static final String rootPath = System.getProperty("user.dir");

	public static String upload(MultipartFile originImage, String directory) {
		String uploadImageName = UUID.randomUUID().toString() + "_" + originImage.getOriginalFilename();
		String uploadImagePath = rootPath + File.separator + directory + File.separator;

		Path uploadPath = Paths.get(uploadImagePath + uploadImageName);

		try {
			originImage.transferTo(uploadPath.toFile());
		} catch (IOException e) {
			return null;
		}

		return uploadImagePath + uploadImageName;
	}
}
