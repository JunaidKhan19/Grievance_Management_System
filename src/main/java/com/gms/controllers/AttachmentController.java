package com.gms.controllers;

import com.gms.dto.AttachmentDTO;
import com.gms.services.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    // Upload multiple files
    @PostMapping("/upload")
    public ResponseEntity<?> uploadFiles(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("parentTable") String parentTable,
            @RequestParam("parentId") String parentId,
            Authentication auth
    ) {
        try {
            for (MultipartFile file : files) {
                // Save file to disk (or cloud)
                String filePath = "/uploads/" + file.getOriginalFilename();
                file.transferTo(new java.io.File(filePath));

                String uploadedBy = auth.getName();
                // Call SP
                attachmentService.addAttachment(parentTable, parentId, file.getOriginalFilename(), filePath, file.getContentType(), uploadedBy);
            }
            return ResponseEntity.ok("Files uploaded successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    // Fetch attachments by parentId
    @GetMapping("/{parentId}")
    public ResponseEntity<List<AttachmentDTO>> getAttachments(@PathVariable String parentId) {
        List<AttachmentDTO> attachments = attachmentService.fetchAttachmentsByParentId(parentId);
        return ResponseEntity.ok(attachments);
    }

    // Deactivate attachment
    @DeleteMapping("/{attachmentId}")
    public ResponseEntity<?> deactivate(@PathVariable Integer attachmentId, @RequestParam String actorId) {
        attachmentService.deactivateAttachment(attachmentId, actorId);
        return ResponseEntity.ok("Attachment deactivated");
    }
}
