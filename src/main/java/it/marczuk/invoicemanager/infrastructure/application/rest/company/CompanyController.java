package it.marczuk.invoicemanager.infrastructure.application.rest.company;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.AddCompanyDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.AddCompanyDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.EditCompanyDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/company")
public class CompanyController {

    private final CompanyServicePort companyServicePort;

    @GetMapping("/get_all")
    public ResponseEntity<List<Company>> getCompanies() {
        return ResponseEntity.ok(companyServicePort.getCompanies());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(companyServicePort.getCompanyById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody AddCompanyDto addCompanyDto) {
        Company company = companyServicePort.addCompany(AddCompanyDtoMapper.mapToCompany(addCompanyDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @PutMapping("/update")
    public ResponseEntity<Company> editCompany(@RequestBody EditCompanyDto editCompanyDto) {
        Company company = companyServicePort.editCompany(EditCompanyDtoMapper.mapToCompany(editCompanyDto));
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteCompany(@PathVariable Long id) {
        companyServicePort.deleteCompany(id);
        return ResponseEntity.ok(id);
    }
}
