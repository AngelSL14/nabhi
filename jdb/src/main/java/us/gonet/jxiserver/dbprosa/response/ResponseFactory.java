package us.gonet.jxiserver.dbprosa.response;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ResponseFactory {

    @Autowired
    ModelMapper modelMapper;

    public ResponseWrapper buildResponseList(List entities,  Class type, String... params)
    {
        ResponseWrapper response = new ResponseWrapper<>();
        if(entities == null || entities.isEmpty())
        {
            response.setCode("01");
            response.addError( new ErrorWS("JDB-01","Registros no encontrados con lo parametros: "+ Arrays.toString(params) ));

        }
        else
        {
            response.setCode("00");
            for(Object a : entities)
            {
                response.addBody(modelMapper.map(a, type));
            }
        }

        return  response;
    }

    public ResponseWrapper buildResponseSingle(Object entity, Class type, String... params)
    {
        ResponseWrapper response = new ResponseWrapper<>();
        if(entity == null)
        {
            response.setCode("01");
            response.addError( new ErrorWS("JDB-01","Registro no encontrado con lo parametros: "+ Arrays.toString(params) ));

        }
        else
        {
            response.setCode("00");
            response.addBody(modelMapper.map(entity, type));
        }
        return response;
    }



}
