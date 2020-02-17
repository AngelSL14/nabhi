package us.gonet.jxiserver.dbprosa.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.ICountryBusiness;
import us.gonet.jxiserver.dbprosa.entity.CountryEntity;
import us.gonet.jxiserver.dbprosa.repository.CountryRepository;
import us.gonet.serverutils.model.jdb.Country;

import java.util.ArrayList;
import java.util.List;

@Component
public class CountryBusinessImpl implements ICountryBusiness {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Iterable<Country> saveBatch(List<Country> countries) {
        CountryEntity countryEntity;
        List<CountryEntity> listEntity = new ArrayList<>();
        for(Country country: countries)
        {
            countryEntity = new CountryEntity(country.getCountryCode(), country.getAlpha2(), country.getAlpha3(), country.getName(), country.getSymbols());
            listEntity.add(countryEntity);
        }
        List<Country> response = new ArrayList<>();
        for(CountryEntity county : countryRepository.saveAll(listEntity))
        {
            Country model = modelMapper.map(county, Country.class);
            response.add(model);
        }

        return response;
    }
}
