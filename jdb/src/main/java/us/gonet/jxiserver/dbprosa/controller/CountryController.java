package us.gonet.jxiserver.dbprosa.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import us.gonet.jxiserver.dbprosa.business.ICountryBusiness;
import us.gonet.jxiserver.dbprosa.repository.CountryRepository;
import us.gonet.jxiserver.dbprosa.security.StreamFilter;
import us.gonet.serverutils.exceptionutils.ServerException;
import us.gonet.serverutils.model.jdb.Country;

import java.util.List;

@RestController
@RequestMapping("country")
@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class CountryController {

    @Autowired
    ICountryBusiness countryBusinessImpl;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StreamFilter streamFilter;

    private static final String[] ALLOWED_FIELDS = new String[] {"countryCode", "alpha2", "alpha3", "name", "symbols"};

    @InitBinder( "Country" )
    public void initBinder ( WebDataBinder binder ) {
        binder.setAllowedFields( ALLOWED_FIELDS );
    }

    @ResponseBody
    @PostMapping("/batch")
    public ResponseEntity saveBatch(@RequestBody List<Country> countries)
    {
        return ResponseEntity.ok(countryBusinessImpl.saveBatch(countries));
    }

    @ResponseBody
    @GetMapping("/{countryCode}")
    public ResponseEntity findByCountryCode(@PathVariable String countryCode)
    {
        try {
            return ResponseEntity.ok(modelMapper.map(countryRepository.findByCountryCode(streamFilter.sanitizeString(countryCode)), Country.class));
        } catch (ServerException e) {
            return ResponseEntity.ok(streamFilter.sanitizeError());
        }
    }

}
