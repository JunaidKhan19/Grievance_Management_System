package com.gms.dto;

import java.time.LocalDateTime;

public class AttachmentDTO {

    private Integer attachmentId;
    private String parentTable;
    private String parentId;
    private String fileName;
    private String filePath;
    private String fileType;
    private String uploadedBy;
    private LocalDateTime uploadedAt;
    private Boolean isActive;

    // Constructor
    public AttachmentDTO(Integer attachmentId, String parentTable, String parentId, String fileName,
                         String filePath, String fileType, String uploadedBy, LocalDateTime uploadedAt,
                         Boolean isActive) {
        this.attachmentId = attachmentId;
        this.parentTable = parentTable;
        this.parentId = parentId;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
        this.uploadedBy = uploadedBy;
        this.uploadedAt = uploadedAt;
        this.isActive = isActive;
    }

    // Getters & Setters
    public Integer getAttachmentId() { return attachmentId; }
    public void setAttachmentId(Integer attachmentId) { this.attachmentId = attachmentId; }

    public String getParentTable() { return parentTable; }
    public void setParentTable(String parentTable) { this.parentTable = parentTable; }

    public String getParentId() { return parentId; }
    public void setParentId(String parentId) { this.parentId = parentId; }

    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFilePath() { return filePath; }
    public void setFilePath(String filePath) { this.filePath = filePath; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    public String getUploadedBy() { return uploadedBy; }
    public void setUploadedBy(String uploadedBy) { this.uploadedBy = uploadedBy; }

    public LocalDateTime getUploadedAt() { return uploadedAt; }
    public void setUploadedAt(LocalDateTime uploadedAt) { this.uploadedAt = uploadedAt; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
