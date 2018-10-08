package cz.muni.fi.pa165.currency;

import org.junit.Test;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.*;


//import CurrencyConvertorImpl;

public class CurrencyConvertorImplTest {

    @Mock
    ExchangeRateTable mockERT;

    Currency EUR = Currency.getInstance("EUR");
    Currency CZK = Currency.getInstance("CZK");

//    @Mock
//    CurrencyConvertorImpl convertor;

    CurrencyConvertorImpl convertor = new CurrencyConvertorImpl(mockERT);

    BigDecimal eurToCzk = new BigDecimal("25");

    @Test
    public void testConvert() throws ExternalServiceFailureException {
        // Don't forget to test border values and proper rounding.
//        fail("Test is not implemented yet.");

//        when(convertor.)

        when(mockERT.getExchangeRate(EUR, CZK)).thenReturn(eurToCzk);
        assertThat(convertor.convert(EUR, CZK, BigDecimal.valueOf(100)) == BigDecimal.valueOf(2500));
        assertThat(convertor.convert(CZK, EUR, BigDecimal.valueOf(100)) == BigDecimal.valueOf(4));
        assertThat(convertor.convert(EUR, EUR, BigDecimal.valueOf(100)) == BigDecimal.valueOf(100));
        assertThat(convertor.convert(CZK, CZK, BigDecimal.valueOf(100)) == BigDecimal.valueOf(100));

    }

    @Test
    public void testConvertWithNullSourceCurrency() throws ExternalServiceFailureException {
//        fail("Test is not implemented yet.");
//        when(mockERT.getExchangeRate(EUR, CZK)).thenReturn(eurToCzk);
//        IllegalArgumentException exc = new IllegalArgumentException("illegal argumet");

        Throwable thrown = catchThrowable(() -> convertor.convert(null, CZK, BigDecimal.valueOf(100)));
        assertThat(thrown).hasMessageContaining("illegal argumet");


//        when(mockERT.getExchangeRate(null, CZK)).thenReturn(BigDecimal.valueOf(0));
//        assertThat(convertor.convert(null, CZK, BigDecimal.valueOf(100)) == BigDecimal.valueOf(0));
    }

    @Test
    public void testConvertWithNullTargetCurrency() throws ExternalServiceFailureException {
//        fail("Test is not implemented yet.");
//        when(mockERT.getExchangeRate(EUR, CZK)).thenReturn(eurToCzk);
//        when(mockERT.getExchangeRate(CZK, null)).thenReturn(BigDecimal.valueOf(0));
//        assertThat(convertor.convert(CZK, null, BigDecimal.valueOf(100)) == BigDecimal.valueOf(0));
        Throwable thrown = catchThrowable(() -> convertor.convert(CZK, null, BigDecimal.valueOf(100)));
        assertThat(thrown).hasMessageContaining("illegal argumet");
    }

    @Test
    public void testConvertWithNullSourceAmount() throws ExternalServiceFailureException {
//        fail("Test is not implemented yet.");
//        when(mockERT.getExchangeRate(EUR, CZK)).thenReturn(eurToCzk);
////        when(mockERT.getExchangeRate(null, CZK)).thenReturn(BigDecimal.valueOf(0));
//        assertThat(convertor.convert(EUR, CZK, null) == BigDecimal.valueOf(0));
//        assertThat(convertor.convert(CZK, EUR, null) == BigDecimal.valueOf(0));
        Throwable thrown = catchThrowable(() -> convertor.convert(EUR, CZK, null));
        assertThat(thrown).hasMessageContaining("illegal argumet");

    }

    @Test
    public void testConvertWithUnknownCurrency() {
//        fail("Test is not implemented yet.");
        Currency unknown = Currency.getInstance("USD");
        Throwable thrown = catchThrowable(() -> convertor.convert(unknown, CZK, BigDecimal.valueOf(100)));
        assertThat(thrown).hasMessageContaining("unknown currency");
    }

    @Test
    public void testConvertWithExternalServiceFailure() {
//        fail("Test is not implemented yet.");
    }

}
