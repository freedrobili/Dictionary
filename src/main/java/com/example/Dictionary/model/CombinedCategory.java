package com.example.Dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CombinedCategory {
    private String categoryCode;
    private String product;
    private Boolean viewToClients;
    private Boolean viewToManagers;
    private Boolean needsDocs;
    private String needsDocsReason;
    private String needsDocsList;
    private Boolean risky;
    private String code; // Поле из CategoryTab
    private String name; // Поле из CategoryTab

}

