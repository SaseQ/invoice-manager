package it.marczuk.invoicemanager.infrastructure.application.rest.company;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.AddCompanyDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.AddCompanyDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.EditCompanyDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/company")
public class CompanyController {

    private final CompanyServicePort companyServicePort;

    @GetMapping("/get_all")
    public List<Company> getCompanies() {
        return companyServicePort.getCompanies();
    }

    @GetMapping("/get/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyServicePort.getCompanyById(id);
    }

    @PostMapping("/add")
    public Company addCompany(@RequestBody AddCompanyDto addCompanyDto) {
        return companyServicePort.addCompany(AddCompanyDtoMapper.mapToCompany(addCompanyDto));
    }

    @PutMapping("/update")
    public Company editCompany(@RequestBody EditCompanyDto editCompanyDto) {
        return companyServicePort.editCompany(EditCompanyDtoMapper.mapToCompany(editCompanyDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable Long id) {
        companyServicePort.deleteCompany(id);
    }
}
