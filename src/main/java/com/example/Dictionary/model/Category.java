package com.example.Dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Table(name="product_category_tab", schema = "dictionary")
@Entity
public class Category {
    @Id
    private int category_code;
    @Column(name = "product")
    private String producy;
    @Column(name = "needsDocs")
    private boolean needsDocs;
    @Column(name = "needsDocsReason")
    private String needsDocsReason;
    @Column(name = "needsDocsList")
    private String needsDocsList;

    public Category() {

    }
}

