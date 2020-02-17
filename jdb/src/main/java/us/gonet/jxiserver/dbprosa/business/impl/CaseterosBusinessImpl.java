


package us.gonet.jxiserver.dbprosa.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.ICaseterosBusiness;
import us.gonet.jxiserver.dbprosa.entity.CaseterosEntity;
import us.gonet.jxiserver.dbprosa.entity.DeviceStatusEntity;
import us.gonet.jxiserver.dbprosa.repository.AtmRepository;
import us.gonet.jxiserver.dbprosa.repository.CaseteroRepository;
import us.gonet.jxiserver.dbprosa.repository.DeviceStatusRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;

import java.util.ArrayList;
import java.util.List;

@Component("caseterosBus")
public class CaseterosBusinessImpl implements ICaseterosBusiness{
    @Autowired
    CaseteroRepository caseteroRepository;

    @Autowired
    DeviceStatusRepository deviceStatusRepository;

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    @Override
    public ResponseWrapper<Casetero> saveBatch(List<Casetero> caseteros, Integer deviceId) {
        DeviceStatusEntity deviceEntity = null;
        try {
            deviceEntity = deviceStatusRepository.getOne(Integer.parseInt(streamFilter.sanitizeString(String.valueOf(deviceId))));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        List<CaseterosEntity> caseterosEntities = modelListToEntity(caseteros, deviceEntity);
        Iterable it = caseteroRepository.saveAll(caseterosEntities);

        return responseFactory.buildResponseList(entityIterableToList(it), Casetero.class, deviceId.toString());
    }


    public List<CaseterosEntity> modelListToEntity(List<Casetero> caseteros, DeviceStatusEntity deviceEntity)
    {
        List<CaseterosEntity> caseterosEntities = new ArrayList<>();
        CaseterosEntity caseterosEntity = null;
        for(Casetero casetero: caseteros)
        {
            caseterosEntity  = modelMapper.map(casetero, CaseterosEntity.class);
            caseterosEntity.setDevice(deviceEntity);

            caseterosEntities.add(caseterosEntity);
        }

        return caseterosEntities;
    }

    public List<CaseterosEntity> entityIterableToList(Iterable<CaseterosEntity> entityList)
    {
        List<CaseterosEntity> response = new ArrayList<>();
        for(CaseterosEntity casetero: entityList)
        {
            response.add(casetero);
        }

        return response;
    }



}
