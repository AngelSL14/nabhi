package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.entity.CaseterosEntity;
import us.gonet.jxiserver.dbprosa.entity.DeviceStatusEntity;
import us.gonet.jxiserver.dbprosa.entity.PrinterEntity;
import us.gonet.jxiserver.dbprosa.esq.AtmEsqEntity;
import us.gonet.jxiserver.dbprosa.esq.AtmEsqRepository;
import us.gonet.jxiserver.dbprosa.repository.CaseteroRepository;
import us.gonet.jxiserver.dbprosa.repository.PrinterDeviceRepository;
import us.gonet.jxiserver.dbprosa.repository.lite.JournalRepositoryLite;
import us.gonet.jxiserver.dbprosa.response.ResponseFactory;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Casetero;
import us.gonet.serverutils.model.jdb.County;
import us.gonet.serverutils.model.jdb.esq.CasseteSingle;
import us.gonet.serverutils.model.jdb.esq.DeviceSingle;
import us.gonet.serverutils.model.jdb.esq.ESQ;
import us.gonet.serverutils.model.jdb.esq.Journal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("esq")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class EsqController
{

    @Autowired
    private CaseteroRepository caseteroRepository;

    @Autowired
    private AtmEsqRepository atmEsqRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ResponseFactory responseFactory;

    @Autowired
    private PrinterDeviceRepository printerDeviceRepository;

    @Autowired
    JournalRepositoryLite journalRepositoryLite;

    @Autowired
    StreamFilter streamFilter;

    @ResponseBody
    @GetMapping("/{terminalId}")
    public ESQ getAtm(@PathVariable String terminalId)
    {
        ESQ esq = new ESQ();
        AtmEsqEntity esqEntity = null;
        try {
            esqEntity = atmEsqRepository.findByTerminalId(streamFilter.sanitizeString(terminalId));
        } catch (ServerException e) {
            return esq;
        }
        esq.setTerminalId(esqEntity.getTerminalId());
        esq.setIp(esqEntity.getIp());
        esq.setFiid(esqEntity.getId().getIdf().getFiid());
        esq.setCounty(modelMapper.map(esqEntity.getId().getCounty(), County.class));
        List<DeviceSingle> deviceSingleList = new ArrayList<>();
        for(DeviceStatusEntity e : esqEntity.getDevices())
        {
            DeviceSingle deviceSingle = new DeviceSingle();
            deviceSingle.setIdDevice(e.getId());
            deviceSingle.setStatus(e.getStatus());
            deviceSingle.setType(e.getDevice().getType());
            if(e.getDevice().getType().equals("DISPENSER") || e.getDevice().getType().equals("CASHINMODULE")
               || e.getDevice().getType().equals("COINSDISPENSER")  )
            {
                deviceSingle.setContent(getCaseteros(e.getId()));
            }
            if(e.getDevice().getType().equals("PRINTER"))
            {
                deviceSingle.setContent(getPrinterContent(e.getId()));
            }

            deviceSingleList.add(deviceSingle);
        }
        esq.setDevices(deviceSingleList);
        try {
            esq.setJournal(journalRepositoryLite.findByAtm(streamFilter.sanitizeString(esqEntity.getTerminalId())).stream().map(
                    journal -> {
                        Journal j = new Journal();
                        j.setAtm(journal.getAtm());
                        j.setMessage(journal.getMessage());
                        j.setWriteDate( new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(journal.getWriteDate()));
                        return j;
                    }
            ).collect(Collectors.toList()));
        } catch (ServerException e) {
            return new ESQ();
        }
        return esq;


    }

    private List<Casetero> getCaseteros(int deviceStatusId)
    {
        List<CaseterosEntity> entities = null;
        try {
            entities = caseteroRepository.findByDeviceId(Integer.parseInt(streamFilter.sanitizeString(String.valueOf(deviceStatusId))));
        } catch (ServerException e) {
            return new ArrayList<>();
        }
        return responseFactory.buildResponseList(entities, CasseteSingle.class, "").getBody();
    }

    private List<Map> getPrinterContent(int deviceStatusId)
    {
        PrinterEntity entity = null;
        try {
            entity = printerDeviceRepository.findByDeviceId(Integer.parseInt(streamFilter.sanitizeString(String.valueOf(deviceStatusId))));
        } catch (ServerException e) {
            return new ArrayList<>();
        }
        Map<String, String> printer = new HashMap<>();
        List<Map> mapList = new ArrayList<>();
        printer.put("paper", entity.getPaper());
        printer.put("toner", entity.getToner());
        mapList.add(printer);
        return mapList;
    }

}
