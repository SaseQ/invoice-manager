package it.marczuk.invoicemanager.infrastructure.application.rest.address;

import it.marczuk.invoicemanager.domain.address.model.Address;
import it.marczuk.invoicemanager.domain.address.port.AddressServicePort;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.AddAddressDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.dto.EditAddressDto;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper.AddAddressDtoMapper;
import it.marczuk.invoicemanager.infrastructure.application.rest.address.mapper.EditAddressDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/invoice_manager/address")
public class AddressController {

    private final AddressServicePort addressServicePort;

    @GetMapping("/get_all")
    public ResponseEntity<List<Address>> getAddresses() {
        return ResponseEntity.ok(addressServicePort.getAddresses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        return ResponseEntity.ok(addressServicePort.getAddressById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody AddAddressDto addAddressDto) {
        Address address = addressServicePort.addAddress(AddAddressDtoMapper.mapToAddress(addAddressDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(address);
    }

    @PutMapping("/update")
    public ResponseEntity<Address> editAddress(@RequestBody EditAddressDto editAddressDto) {
        Address address = addressServicePort.editAddress(EditAddressDtoMapper.mapToAddress(editAddressDto));
        return ResponseEntity.ok(address);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteAddress(@PathVariable Long id) {
        addressServicePort.deleteAddress(id);
        return ResponseEntity.ok(id);
    }
}
