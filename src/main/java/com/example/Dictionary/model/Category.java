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
    private Integer categoryCode;
    @Column(name = "product")
    private String product;
    @Column(name = "needs_docs")
    private Boolean needsDocs;
    @Column(name = "needs_docs_reason")
    private String needsDocsReason;
    @Column(name = "needs_docs_list")
    private String needsDocsList;

    public Category() {

    }
}

