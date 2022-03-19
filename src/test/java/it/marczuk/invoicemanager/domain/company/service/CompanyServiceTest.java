package it.marczuk.invoicemanager.domain.company.service;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressServicePort;
import it.marczuk.invoicemanager.domain.address.service.TestAddressData;
import it.marczuk.invoicemanager.domain.company.model.Company;
import it.marczuk.invoicemanager.domain.company.port.CompanyRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import it.marczuk.invoicemanager.infrastructure.application.rest.company.dto.EditCompanyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CompanyServiceTest {

    @Mock
    private CompanyRepositoryPort companyRepositoryPort;
    @Mock
    private AddressServicePort addressServicePort;

    private CompanyService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new CompanyService(companyRepositoryPort, addressServicePort);
    }

    @Test
    void shouldGetCompanies() {
        // given
        List<Company> companyList = List.of(TestCompanyData.getTestCompany(1L));
        when(companyRepositoryPort.findAll())
                .thenReturn(companyList);

        // when
        List<Company> actual = underTest.getCompanies();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldGetCompanyById() {
        // given
        Company company = TestCompanyData.getTestCompany(1L);
        when(companyRepositoryPort.findById(company.getId()))
                .thenReturn(Optional.of(company));

        // when
        Company actual = underTest.getCompanyById(company.getId());

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getCompanyName()).isEqualTo("companyName");
        assertThat(actual.getCompanyOwnerFirstName()).isEqualTo("companyOwnerFirstName");
        assertThat(actual.getCompanyOwnerSecondName()).isEqualTo("companyOwnerSecondName");
        assertThat(actual.getTaxIdentificationNumber()).isEqualTo("1234567890");
        assertThat(actual.getAddress()).isEqualTo(TestAddressData.getTestAddress(1L));
    }

    @Test
    void shouldReturn404fromGetCompanyById() {
        // given
        Company company = TestCompanyData.getTestCompany(1L);
        when(companyRepositoryPort.findById(company.getId()))
                .thenReturn(Optional.of(company));

        // then
        assertThatThrownBy(() -> underTest.getCompanyById(2L))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Could not find company by id: 2");
    }

    @Test
    void shouldAddCompany() {
        // given
        Company company = TestCompanyData.getTestCompany(null);
        Address address = company.getAddress();
        when(companyRepositoryPort.save(company))
                .thenAnswer(invocationOnMock -> {
                    company.setId(1L);
                    return company;
                });
        when(addressServicePort.addAddress(address))
                .thenAnswer(invocationOnMock -> {
                    address.setId(1L);
                    company.setAddress(address);
                    return address;
                });

        // when
        Company actual = underTest.addCompany(company);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getCompanyName()).isEqualTo("companyName");
        assertThat(actual.getCompanyOwnerFirstName()).isEqualTo("companyOwnerFirstName");
        assertThat(actual.getCompanyOwnerSecondName()).isEqualTo("companyOwnerSecondName");
        assertThat(actual.getTaxIdentificationNumber()).isEqualTo("1234567890");
        assertThat(actual.getAddress()).isEqualTo(TestAddressData.getTestAddress(1L));
    }

    @Test
    void shouldEditCompany() {
        // given
        Company company = TestCompanyData.getTestCompany(1L);
        Address address = company.getAddress();
        when(companyRepositoryPort.findById(company.getId()))
                .thenReturn(Optional.of(company));
        when(addressServicePort.getAddressById(address.getId()))
                .thenReturn(address);
        when(companyRepositoryPort.save(company))
                .thenAnswer(invocationOnMock -> {
                    company.setCompanyName("editedCompanyName");
                    company.setCompanyOwnerFirstName("editedCompanyOwnerFirstName");
                    company.setCompanyOwnerSecondName("editedCompanyOwnerSecondName");
                    company.setTaxIdentificationNumber("0123456789");
                    company.setAddress(address);
                    return company;
                });

        // when
        EditCompanyDto editCompanyDto = TestCompanyData.mapToEditCompanyDto(company);
        Company actual = underTest.editCompany(editCompanyDto);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getCompanyName()).isEqualTo("editedCompanyName");
        assertThat(actual.getCompanyOwnerFirstName()).isEqualTo("editedCompanyOwnerFirstName");
        assertThat(actual.getCompanyOwnerSecondName()).isEqualTo("editedCompanyOwnerSecondName");
        assertThat(actual.getTaxIdentificationNumber()).isEqualTo("0123456789");
        assertThat(actual.getAddress()).isEqualTo(TestAddressData.getTestAddress(1L));
    }

    @Test
    void shouldDeleteCompany() {
        // given
        Company company = TestCompanyData.getTestCompany(1L);
        Address address = company.getAddress();
        when(companyRepositoryPort.findById(company.getId()))
                .thenReturn(Optional.of(company));
        when(addressServicePort.getAddressById(address.getId()))
                .thenReturn(address);

        // when
        underTest.deleteCompany(company.getId());

        // then
        verify(companyRepositoryPort).delete(company);
    }
}