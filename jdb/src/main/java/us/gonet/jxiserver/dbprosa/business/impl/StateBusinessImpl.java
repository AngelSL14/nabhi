package us.gonet.jxiserver.dbprosa.business.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.gonet.jxiserver.dbprosa.business.IStateBusiness;
import us.gonet.jxiserver.dbprosa.entity.StateEntity;
import us.gonet.jxiserver.dbprosa.repository.StateRepository;
import us.gonet.serverutils.model.jdb.State;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateBusinessImpl implements IStateBusiness {

    @Autowired
    StateRepository stateRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Iterable<State> saveBatch(List<State> states) {

        StateEntity entity = null;
        List<StateEntity> listEntity = new ArrayList<>();
        for(State state: states)
        {
            entity = new StateEntity(state.getStateCode(), state.getStateName(), state.getStateShortName(), state.getZone());
            listEntity.add(entity);
        }
        List<State> response = new ArrayList<>();
        for(StateEntity stateEntity : stateRepository.saveAll(listEntity))
        {
            State model = modelMapper.map(stateEntity, State.class);
            response.add(model);
        }

        return response;
    }
}
