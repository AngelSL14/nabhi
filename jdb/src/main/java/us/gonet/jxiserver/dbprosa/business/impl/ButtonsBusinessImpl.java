package us.gonet.jxiserver.dbprosa.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.IButtonsBusiness;
import us.gonet.jxiserver.dbprosa.entity.AtmEntity;
import us.gonet.jxiserver.dbprosa.entity.ButtonsEntity;
import us.gonet.jxiserver.dbprosa.repository.AtmRepository;
import us.gonet.jxiserver.dbprosa.repository.ButtonsRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Button;

import java.util.ArrayList;
import java.util.List;

@Component("buttonsBus")
public class ButtonsBusinessImpl implements IButtonsBusiness {
    @Autowired
    ButtonsRepository buttonsRepository;

    @Autowired
    AtmRepository atmRepository;

    @Autowired
    ResponseFactory responseFactory;


    @Autowired
    StreamFilter streamFilter;

    @Override
    public ResponseWrapper<Button> saveBatch(List<Button> buttons, String atmId) {
        AtmEntity atmEntity = null;
        try {
            atmEntity = atmRepository.getOne(streamFilter.sanitizeString(atmId));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        ButtonsEntity buttonsEntity = null;
        List<ButtonsEntity> listEntity = new ArrayList<>();
        for (Button button : buttons) {
            buttonsEntity = new ButtonsEntity(atmEntity, button.getScreen(), button.getActiveFdk());
            buttonsEntity.setId(button.getId());
            listEntity.add(buttonsEntity);
        }
        List<ButtonsEntity> entities = new ArrayList<>();
        for (ButtonsEntity e : buttonsRepository.saveAll(listEntity))
        {
            entities.add(e);
        }

        ResponseWrapper<Button> response = responseFactory.buildResponseList(entities, Button.class, atmId);
        response.setCode("00");

        return response;
    }
}
