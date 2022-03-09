package it.marczuk.invoicemanager.infrastructure.persistance.database.product.repository;

import it.marczuk.invoicemanager.infrastructure.persistance.database.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
