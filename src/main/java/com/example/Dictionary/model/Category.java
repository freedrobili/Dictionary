package com.example.Dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Table(name="product_category_tab", schema = "dictionary")
@Entity
public class Category {
    @Id
    @Column(name = "category_code")
    private String categoryCode;

    @ManyToOne
    @JoinColumn(name = "category_code", referencedColumnName = "code", insertable = false, updatable = false)
    private CategoryTab categoryTab;

    @Column(name = "product")
    private String product;
    @Column(name = "view_to_clients")
    private Boolean viewToClients;
    @Column(name = "view_to_managers")
    private Boolean viewToManagers;
    @Column(name = "needs_docs")
    private Boolean needsDocs;
    @Column(name = "needs_docs_reason")
    private String needsDocsReason;
    @Column(name = "needs_docs_list")
    private String needsDocsList;
    @Column(name = "risky")
    private Boolean risky;


    public Category() {

    }
}

