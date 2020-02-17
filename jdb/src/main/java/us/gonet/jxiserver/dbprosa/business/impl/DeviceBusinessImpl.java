package us.gonet.jxiserver.dbprosa.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.IDeviceBusiness;
import us.gonet.jxiserver.dbprosa.entity.DeviceEntity;
import us.gonet.jxiserver.dbprosa.repository.DeviceRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.serverutils.model.jdb.Device;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeviceBusinessImpl implements IDeviceBusiness{

    @Autowired
    DeviceRepository deviceRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;

    @Override
    public ResponseWrapper<Device> saveBatch(List<Device> devices) {
        DeviceEntity entity;
        List<DeviceEntity> listEntity = new ArrayList<>();
        for(Device device: devices)
        {
            entity =  new DeviceEntity();
            entity.setId(device.getIdDevice());
            entity.setType(device.getType());

            listEntity.add(entity);
        }
        return responseFactory.buildResponseList(deviceRepository.saveAll(listEntity), Device.class, devices.toString());


    }
}
