package com.library.recetas.controller;

import com.library.recetas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;

@RestController
@RequestMapping("/api/users/me")
@RequiredArgsConstructor
public class ImageUploadController {

    private final UsuarioService usuarioService;

    // Define the directory where images will be stored.
    // It's recommended to configure this path via application.properties or environment variables in a real application.
    private static final String UPLOAD_DIR = "src/main/resources/static/images/avatars";

    /**
     * Uploads a profile image for the authenticated user.
     *
     * @param file The image file to upload.
     * @param principal The authenticated user's principal, used to get the user's ID.
     * @return ResponseEntity with a success message or an error.
     */
    @PostMapping("/avatar")
    public ResponseEntity<String> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            Principal principal) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Get the authenticated user's ID. This assumes the Principal provides user details.
            // In a real app, you might fetch the user object directly from the service.
            // For simplicity, we'll assume the username/email from Principal can be used to find the user.
            // A more robust approach would be to inject a UserDetails object or fetch user by ID.
            String userEmail = principal.getName(); // Assuming email is used as username in Principal
            // Fetch user ID from service or repository if needed for file naming or association.
            // For now, we'll use a generic naming convention and rely on the avatarUrl field.

            // Create the upload directory if it doesn't exist
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Generate a unique filename to avoid overwriting and potential security issues
            // Using user's email (sanitized) and original file extension
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            // Sanitize email for filename
            String sanitizedEmail = userEmail.replace("@", "_").replace(".", "_");
            String newFileName = sanitizedEmail + "_avatar" + fileExtension;
            Path filePath = uploadPath.resolve(newFileName);

            // Save the file
            Files.copy(file.getInputStream(), filePath);

            // Construct the URL for the uploaded image
            // This URL assumes the static resources are served from the root context path.
            // Adjust if your application serves static content from a different path.
            String avatarUrl = "/images/avatars/" + newFileName;

            // Update the user's avatar URL in the database
            // We need to fetch the user first to update their avatarUrl.
            // This requires a method in UsuarioService to update avatar URL by email.
            usuarioService.updateAvatarUrl(userEmail, avatarUrl);

            return ResponseEntity.ok("Avatar uploaded successfully. URL: " + avatarUrl);

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload avatar: " + e.getMessage());
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
