package com.gms.services;

import com.gms.dto.AttachmentDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttachmentService {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void addAttachment(String parentTable, String parentId, String fileName, String filePath, String fileType, String uploadedBy) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("add_attachment");
        query.registerStoredProcedureParameter("p_parentTable", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_parentId", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_fileName", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_filePath", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_fileType", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_uploadedBy", String.class, ParameterMode.IN);

        query.setParameter("p_parentTable", parentTable);
        query.setParameter("p_parentId", parentId);
        query.setParameter("p_fileName", fileName);
        query.setParameter("p_filePath", filePath);
        query.setParameter("p_fileType", fileType);
        query.setParameter("p_uploadedBy", uploadedBy);

        query.execute();
    }

    public List<AttachmentDTO> fetchAttachmentsByParentId(String parentId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("fetch_attachments_by_parentId");
        query.registerStoredProcedureParameter("p_parentId", String.class, ParameterMode.IN);
        query.setParameter("p_parentId", parentId);

        List<Object[]> results = query.getResultList();
        List<AttachmentDTO> attachments = new ArrayList<>();
        for (Object[] row : results) {
            attachments.add(new AttachmentDTO(
                    ((Number) row[0]).intValue(),     // attachmentId
                    (String) row[1],                  // parentTable
                    (String) row[2],                  // parentId (as String)
                    (String) row[3],                  // fileName
                    (String) row[4],                  // filePath
                    (String) row[5],                  // fileType
                    (String) row[6],                  // uploadedBy
                    row[7] != null ? ((java.sql.Timestamp) row[7]).toLocalDateTime() : LocalDateTime.now(), // uploadedAt
                    (Boolean) row[8]                  // isActive
            ));
        }
        return attachments;
    }

    @Transactional
    public void deactivateAttachment(Integer attachmentId, String actorId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("deactivate_attachment");
        query.registerStoredProcedureParameter("p_attachmentId", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_actorId", String.class, ParameterMode.IN);

        query.setParameter("p_attachmentId", attachmentId);
        query.setParameter("p_actorId", actorId);

        query.execute();
    }
}
