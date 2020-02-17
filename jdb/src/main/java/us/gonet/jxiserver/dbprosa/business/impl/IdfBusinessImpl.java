package us.gonet.jxiserver.dbprosa.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.IIdfBusiness;
import us.gonet.jxiserver.dbprosa.entity.CountryEntity;
import us.gonet.jxiserver.dbprosa.entity.IDFEntity;
import us.gonet.jxiserver.dbprosa.repository.CountryRepository;
import us.gonet.jxiserver.dbprosa.repository.IDFRepository;
import us.gonet.serverutils.model.jdb.IDF;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class IdfBusinessImpl implements IIdfBusiness {

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    IDFRepository idfRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Iterable<IDF> saveBatch(List<IDF> idfs) {
        List<CountryEntity> countryEntities = countryRepository.findAll();
        IDFEntity entity = null;
        List<IDFEntity> listEntity = new ArrayList<>();
        for(IDF idf: idfs)
        {
            CountryEntity country = countryEntities.stream().filter(countryEntity ->
                    countryEntity.getCountryCode().equals(idf.getCountry().getCountryCode())).collect(Collectors.toList()).get(0);

            entity = new IDFEntity(idf.getFiid(), idf.getLogicalNet(), idf.getName(), idf.getAcquiringId(), idf.getNameLong(), country);
            listEntity.add(entity);
        }
        List<IDF> response = new ArrayList<>();
        for(IDFEntity idf : idfRepository.saveAll(listEntity))
        {
            IDF model = modelMapper.map(idf, IDF.class);
            response.add(model);
        }

        return response;
    }
}
