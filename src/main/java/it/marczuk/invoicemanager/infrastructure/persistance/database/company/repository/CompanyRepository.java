package it.marczuk.invoicemanager.infrastructure.persistance.database.company.repository;

import it.marczuk.invoicemanager.infrastructure.persistance.database.company.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
