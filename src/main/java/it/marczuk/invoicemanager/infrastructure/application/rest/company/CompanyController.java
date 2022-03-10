package it.marczuk.invoicemanager.infrastructure.application.rest.company;

import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.AddCompanyDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.mapper.AddCompanyDtoMapper;
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

    @PostMapping("/add")
    public Company addCompany(@RequestBody AddCompanyDto addCompanyDto) {
        return companyServicePort.addCompany(AddCompanyDtoMapper.mapToCompany(addCompanyDto));
    }
}
