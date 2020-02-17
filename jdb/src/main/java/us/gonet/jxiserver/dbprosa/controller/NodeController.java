package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.NodeProsaEntity;
import us.gonet.jxiserver.dbprosa.repository.NodeProsaRepository;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.NodeProsa;

import java.util.List;

@RestController
@RequestMapping("/nodeProsa")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class NodeController {

    @Autowired
    NodeProsaRepository nodeProsaRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseFactory responseFactory;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] {"idNode", "ip", "port", "tracerNumber",
        "nodeName", "zpk"};

    @InitBinder( "NodeProsa" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @GetMapping("search/byNodeName")
    public ResponseEntity findByNodeName(@RequestParam String nodeName)
    {
        NodeProsa response = null;
        try {
            response = modelMapper.map(nodeProsaRepository.findByNodeName(streamFilter.sanitizeString(nodeName)), NodeProsa.class);
        } catch (ServerException e) {
            ResponseEntity.ok(new NodeProsa());
        }
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("search/{idNode}/and/{nodeName}")
    public ResponseEntity findByNodeName(@PathVariable int idNode, @PathVariable String nodeName)
    {
        NodeProsa response = null;
        try {
            response = modelMapper.map(nodeProsaRepository.findByIdNodeAndNodeName(
                    Integer.parseInt(streamFilter.sanitizeString(String.valueOf(idNode))), streamFilter.sanitizeString(nodeName)), NodeProsa.class);
        } catch (ServerException e) {
            return ResponseEntity.ok(new NodeProsa());
        }
        return ResponseEntity.ok(response.getZpk());
    }

    @ResponseBody
    @GetMapping("/getTraceNumber/{idNode}")
    public ResponseWrapper<NodeProsa> findAndIncrementTraceNumber(@PathVariable int idNode)
    {
        NodeProsaEntity entity = null;
        try {
            entity = nodeProsaRepository.getOne(Integer.parseInt(streamFilter.sanitizeString(String.valueOf(idNode))));
        } catch (ServerException e) {
            return streamFilter.sanitizeError();
        }
        ResponseWrapper<NodeProsa> response = responseFactory.buildResponseSingle(entity, NodeProsa.class, String.valueOf(idNode));
        entity.setTracerNumber(entity.getTracerNumber()+1);
        nodeProsaRepository.save(entity);
        return response;
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<NodeProsa> findAll()
    {
        List<NodeProsaEntity> entities = nodeProsaRepository.findAll();
        return responseFactory.buildResponseList(entities, NodeProsa.class, "");
    }
}