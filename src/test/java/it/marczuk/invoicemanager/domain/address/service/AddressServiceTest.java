package it.marczuk.invoicemanager.domain.address.service;

import com.neovisionaries.i18n.CountryCode;
import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressRepositoryPort;
import it.marczuk.invoicemanager.infrastructure.application.exception.ElementNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddressServiceTest {

    @Mock
    private AddressRepositoryPort addressRepositoryPort;

    private AddressService underTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new AddressService(addressRepositoryPort);
    }

    @Test
    void shouldGetAddresses() {
        // given
        List<Address> addressList = List.of(TestAddressData.getTestAddress(1L));
        when(addressRepositoryPort.findAll())
                .thenReturn(addressList);

        // when
        List<Address> actual = underTest.getAddresses();

        // then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void shouldGetAddressById() {
        // given
        Address address = TestAddressData.getTestAddress(1L);
        when(addressRepositoryPort.findById(address.getId()))
                .thenReturn(Optional.of(address));

        // when
        Address actual = underTest.getAddressById(address.getId());

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getStreetName()).isEqualTo("streetName");
        assertThat(actual.getHouseNumber()).isEqualTo("11");
        assertThat(actual.getZipCode()).isEqualTo("09-032");
        assertThat(actual.getCity()).isEqualTo("city");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.PL);
    }

    @Test
    void shouldReturn404fromGetAddressById() {
        // given
        Address address = TestAddressData.getTestAddress(1L);
        when(addressRepositoryPort.findById(address.getId()))
                .thenReturn(Optional.of(address));

        // then
        assertThatThrownBy(() -> underTest.getAddressById(2L))
                .isInstanceOf(ElementNotFoundException.class)
                .hasMessage("Could not find address by id: 2");
    }

    @Test
    void shouldAddAddress() {
        // given
        Address address = TestAddressData.getTestAddress(null);
        when(addressRepositoryPort.save(address)).thenAnswer(invocationOnMock -> {
            address.setId(1L);
            return address;
        });

        // when
        Address actual = underTest.addAddress(address);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getStreetName()).isEqualTo("streetName");
        assertThat(actual.getHouseNumber()).isEqualTo("11");
        assertThat(actual.getZipCode()).isEqualTo("09-032");
        assertThat(actual.getCity()).isEqualTo("city");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.PL);
    }

    @Test
    void shouldEditAddress() {
        // given
        Address address = TestAddressData.getTestAddress(1L);
        when(addressRepositoryPort.findById(address.getId()))
                .thenReturn(Optional.of(address));
        when(addressRepositoryPort.save(address)).thenAnswer(invocationOnMock -> {
            address.setStreetName("editedStreetName");
            address.setHouseNumber("22");
            address.setZipCode("10-032");
            address.setCity("editedCity");
            address.setCountry(CountryCode.DE);
            return address;
        });

        // when
        Address actual = underTest.editAddress(address);

        // then
        assertThat(actual.getId()).isEqualTo(1L);
        assertThat(actual.getStreetName()).isEqualTo("editedStreetName");
        assertThat(actual.getHouseNumber()).isEqualTo("22");
        assertThat(actual.getZipCode()).isEqualTo("10-032");
        assertThat(actual.getCity()).isEqualTo("editedCity");
        assertThat(actual.getCountry()).isEqualTo(CountryCode.DE);
    }

    @Test
    void shouldDeleteAddress() {
        // given
        Address address = TestAddressData.getTestAddress(1L);
        when(addressRepositoryPort.findById(address.getId()))
                .thenReturn(Optional.of(address));

        // when
        underTest.deleteAddress(address.getId());

        // then
        verify(addressRepositoryPort).delete(address);
    }
}