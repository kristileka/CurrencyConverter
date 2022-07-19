package com.kristileka.eucurrencyconverter.cron.impl;

import com.kristileka.eucurrencyconverter.core.CurrencyBusiness;
import com.kristileka.eucurrencyconverter.cron.CurrencyUpdaterService;
import com.kristileka.eucurrencyconverter.service.filesystem.CsvManagerService;
import com.kristileka.eucurrencyconverter.service.filesystem.ZipManagerService;
import com.kristileka.eucurrencyconverter.service.network.NetworkManagerService;
import com.kristileka.eucurrencyconverter.service.redis.ExchangeRecord;
import com.kristileka.eucurrencyconverter.service.redis.ExchangeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class CurrencyUpdaterServiceImpl implements CurrencyUpdaterService {

    @Autowired
    ZipManagerService zipManagerService;
    @Autowired
    NetworkManagerService networkManagerService;
    @Autowired
    CsvManagerService csvManagerService;

    @Autowired
    CurrencyBusiness currencyBusiness;

    @Override
    public void updateCurrencies() throws IOException {
        String latestContentZip = networkManagerService.getLatestContent();
        if (latestContentZip.isEmpty())
            return;
        String csvPath = zipManagerService.unZipFile(latestContentZip);
        csvManagerService.readCsvExchangeRecords(csvPath);
//        currencyBusiness.updateCurrencies(exchangeRecordList);
    }
}
