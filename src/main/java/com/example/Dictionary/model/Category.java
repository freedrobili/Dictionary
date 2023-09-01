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
    private int category_code;
    @Column(name = "product")
    private String product;
    @Column(name = "needsDocs")
    private boolean needsDocs;
    @Column(name = "needsDocsReason")
    private String needsDocsReason;
    @Column(name = "needsDocsList")
    private String needsDocsList;

    public Category() {

    }
}

