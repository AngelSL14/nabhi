package us.gonet.jxiserver.dbprosa.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.ICountyBusiness;
import us.gonet.jxiserver.dbprosa.entity.CountyEntity;
import us.gonet.jxiserver.dbprosa.entity.StateEntity;
import us.gonet.jxiserver.dbprosa.repository.CountyRepository;
import us.gonet.jxiserver.dbprosa.repository.StateRepository;
import us.gonet.serverutils.model.jdb.County;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CountyBusinessImpl implements ICountyBusiness {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Iterable<County> saveBatch(List<County> counties) {
        List<StateEntity> stateEntities = stateRepository.findAll();
        CountyEntity countyEntity = null;
        List<CountyEntity> listEntity = new ArrayList<>();
        for(County county: counties)
        {
            StateEntity state = stateEntities.stream().filter(stateEntity ->
                    stateEntity.getStateCode().equals(county.getState().getStateCode())).collect(Collectors.toList()).get(0);

            countyEntity = new CountyEntity(county.getCountyCodeId(), county.getCountyCode(), county.getCountyName(), state);
            listEntity.add(countyEntity);
        }
        List<County> response = new ArrayList<>();
        for(CountyEntity county : countyRepository.saveAll(listEntity))
        {
            County model = modelMapper.map(county, County.class);
            response.add(model);
        }

        return response;
    }
}

