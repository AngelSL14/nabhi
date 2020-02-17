package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.IStateBusiness;
import us.gonet.serverutils.model.jdb.State;

import java.util.List;

@RestController
@RequestMapping("state")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class StateController {

    @Autowired
    IStateBusiness stateBusinessImpl;


    private static final String[] ALLOWED_FIELDS = new String[] {"stateCode", "stateName", "stateShortName", "zone"};

    @InitBinder( "State" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }


    @ResponseBody
    @PostMapping("/batch")
    public ResponseEntity saveBatch(@RequestBody List<State> states)
    {
        return ResponseEntity.ok(stateBusinessImpl.saveBatch(states));
    }
}
