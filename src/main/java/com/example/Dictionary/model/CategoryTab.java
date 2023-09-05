package com.example.Dictionary.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "category_tab", schema = "dictionary")
public class CategoryTab {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public CategoryTab() {

    }
}
