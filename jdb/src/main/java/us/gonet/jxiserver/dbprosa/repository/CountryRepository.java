package us.gonet.jxiserver.dbprosa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import us.gonet.jxiserver.dbprosa.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, String > {

    CountryEntity findByCountryCode(String countryCode);
}
