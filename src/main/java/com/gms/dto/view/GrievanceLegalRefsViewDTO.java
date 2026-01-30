package com.gms.dto.view;

import com.gms.dto.LegalRefResponseDTO;

import java.util.List;

public class GrievanceLegalRefsViewDTO {

    private String grvnNum;
    private List<LegalRefResponseDTO> legalReferences;

    public String getGrvnNum() {
        return grvnNum;
    }

    public void setGrvnNum(String grvnNum) {
        this.grvnNum = grvnNum;
    }

    public List<LegalRefResponseDTO> getLegalReferences() {
        return legalReferences;
    }

    public void setLegalReferences(List<LegalRefResponseDTO> legalReferences) {
        this.legalReferences = legalReferences;
    }
}
