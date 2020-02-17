package us.gonet.jxiserver.dbprosa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.lite.JournalEntityLite;
import us.gonet.jxiserver.dbprosa.repository.lite.JournalRepositoryLite;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.response.ResponseWrapper;

import java.sql.Timestamp;

@RestController
@RequestMapping("journal")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class JournalController {

    @Autowired
    JournalRepositoryLite journalRepositoryLite;

    @Autowired
    ResponseFactory responseFactory;

    @ResponseBody
    @PostMapping("/write/{atm}")
    public ResponseWrapper<JournalEntityLite> writeJournal(@PathVariable String atm, @RequestBody String message)
    {
        JournalEntityLite entity = new JournalEntityLite();
        entity.setAtm(atm);
        entity.setMessage(message);
        entity.setWriteDate(new Timestamp(System.currentTimeMillis()));
        return responseFactory.buildResponseSingle(journalRepositoryLite.save(entity), JournalEntityLite.class, atm);
    }

    @ResponseBody
    @GetMapping()
    public ResponseWrapper<JournalEntityLite> getAllJournal()
    {
        return responseFactory.buildResponseList(journalRepositoryLite.findAll(), JournalEntityLite.class, "");
    }
}
