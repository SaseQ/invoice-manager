package it.marczuk.invoicemanager.infrastructure.application.rest.address;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.AddAddressDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.EditAddressDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper.AddAddressDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper.EditAddressDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/address")
public class AddressController {

    private final AddressServicePort addressServicePort;

    @GetMapping("/get_all")
    public List<Address> getAddresses() {
        return addressServicePort.getAddresses();
    }

    @GetMapping("/get/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressServicePort.getAddressById(id);
    }

    @PostMapping("/add")
    public Address addAddress(@RequestBody AddAddressDto addAddressDto) {
        return addressServicePort.addAddress(AddAddressDtoMapper.mapToAddress(addAddressDto));
    }

    @PutMapping("/update")
    public Address editAddress(@RequestBody EditAddressDto editAddressDto) {
        return addressServicePort.editAddress(EditAddressDtoMapper.mapToAddress(editAddressDto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAddress(@PathVariable Long id) {
        addressServicePort.deleteAddress(id);
    }
}
