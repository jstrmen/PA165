package cz.muni.fi.pa165.currency;

import java.math.BigDecimal;
import java.util.Currency;


/**
 * This is base implementation of {@link CurrencyConvertor}.
 *
 * @author petr.adamek@embedit.cz
 */
public class CurrencyConvertorImpl implements CurrencyConvertor {

    private final ExchangeRateTable exchangeRateTable;
    //private final Logger logger = LoggerFactory.getLogger(CurrencyConvertorImpl.class);

    public CurrencyConvertorImpl(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    @Override
    public BigDecimal convert(Currency sourceCurrency, Currency targetCurrency, BigDecimal sourceAmount) {
        if (sourceAmount == null || sourceCurrency == null || targetCurrency == null)
            throw new IllegalArgumentException("illegal argumet");
        BigDecimal rate = null;
        try {
            rate = exchangeRateTable.getExchangeRate(sourceCurrency, targetCurrency);
        }catch (IllegalArgumentException exc){
            throw new UnknownExchangeRateException("unknown currency");
        } catch (ExternalServiceFailureException e) {
            e.printStackTrace();
        }

        return sourceAmount.multiply(rate);

//        throw new UnsupportedOperationException("Not implemented yet.");
    }

}
