package com.gms.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachmentId")
    private Integer attachmentId;

    @Column(name = "parentTable", length = 50, nullable = false)
    private String parentTable;

    @Column(name = "parentId", nullable = false, length = 4, columnDefinition = "CHAR(4)")
    private String parentId;

    @Column(name = "fileName", length = 255, nullable = false)
    private String fileName;

    @Column(name = "filePath", length = 500, nullable = false)
    private String filePath;

    @Column(name = "fileType", length = 50)
    private String fileType;

    @Column(name = "uploadedBy", length = 4, nullable = false, columnDefinition = "CHAR(4)")
    private String uploadedBy;

    @Column(name = "uploadedAt", insertable = false, updatable = false)
    private LocalDateTime uploadedAt;

    @Column(name = "isActive", insertable = false)
    private Boolean isActive;

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
    public void setIsActive(Boolean active) { isActive = active; }
}
