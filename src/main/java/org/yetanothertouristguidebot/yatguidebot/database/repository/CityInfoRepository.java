package org.yetanothertouristguidebot.yatguidebot.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yetanothertouristguidebot.yatguidebot.database.model.CityInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityInfoRepository extends JpaRepository<CityInfo, Long> {

	Optional<List<CityInfo>> findAllByNameEquals(String name);

	Optional<CityInfo> findByNameAndCountryEquals(String name, String country);
}
