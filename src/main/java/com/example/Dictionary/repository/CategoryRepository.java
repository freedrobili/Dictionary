package com.example.Dictionary.repository;

import com.example.Dictionary.model.Category;
import com.example.Dictionary.model.CombinedCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tinkoff.eacq.mma.dictionary.rest.model.CategoriesResponse;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByProduct(String productName);

    @Query("SELECT new com.example.Dictionary.model.CombinedCategory(c.categoryCode, c.product, c.viewToClients, c.viewToManagers, c.needsDocs, c.needsDocsReason, c.needsDocsList, c.risky, ct.code, ct.name) " +
            "FROM Category c JOIN c.categoryTab ct " +
            "WHERE (:code is null or c.categoryCode = :code) " +
            "AND (:name is null or ct.name = :name) " +
            "AND (:viewToClients is null or c.viewToClients = :viewToClients) " +
            "AND (:viewToManagers is null or c.viewToManagers = :viewToManagers) " +
            "AND (:needsDocs is null or c.needsDocs = :needsDocs) " +
            "AND (:product is null or c.product = :product) " +
            "AND (:risky is null or c.risky = :risky)")
    List<CombinedCategory> findCategories(
            @Param("code") String code,
            @Param("name") String name,
            @Param("viewToClients") Boolean viewToClients,
            @Param("viewToManagers") Boolean viewToManagers,
            @Param("needsDocs") Boolean needsDocs,
            @Param("product") String product,
            @Param("risky") Boolean risky
    );
}