package it.marczuk.invoicemanager.infrastructure.webclient.tax;

import it.marczuk.invoicemanager.domain.common.taxclient.TaxClientPort;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.TaxDto;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.dto.VatStackDto;
import it.marczuk.invoicemanager.infrastructure.webclient.tax.mapper.TaxDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
public class TaxClientAdapter implements TaxClientPort {

    private static final String TAX_IO_URL = "https://api.vatstack.com/v1/";
    private static final String API_KEY = "pk_live_2bdb532f1dfc13bde95990478a025f65";

    private final RestTemplate restTemplate;

    public TaxDto getTax(String countryCode) {
        VatStackDto vatStackDto = callGetMethod("rates?country_code={countryCode}", VatStackDto.class, countryCode);
        return TaxDtoMapper.mapToTaxDto(vatStackDto);
    }

    private <T> T callGetMethod(String url, Class<T> responseType, Object... objects) {
        ResponseEntity<T> response = restTemplate.exchange(TAX_IO_URL + url, HttpMethod.GET, getHttpEntity(), responseType, objects);
        return response.getBody();
    }

    private HttpEntity getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-API-KEY", API_KEY);

        return new HttpEntity(headers);
    }
}
