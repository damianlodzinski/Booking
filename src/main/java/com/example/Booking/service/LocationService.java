package com.example.Booking.service;

import com.example.Booking.entity.LocationEntity;
import com.example.Booking.model.LocationModel;
import com.example.Booking.model.api.BrokerLocationDTO;
import com.example.Booking.repository.LocationRepository;
import com.example.Booking.service.api.TokenService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final RestTemplate restTemplate;
    private final TokenService tokenService;
    private final LocationRepository locationRepository;
    private final ModelMapper modelMapper;
    @Value("${api.main.url}")
    private String apiMainUrl;
    @Value("${api.locations.url}")
    private String apiLocationsUrl;


    @Scheduled(cron = "@daily")
    public BrokerLocationDTO[] getLocationsFromApi() {
        BrokerLocationDTO[] brokerLocationDTOS =
                restTemplate.exchange(apiMainUrl + apiLocationsUrl, HttpMethod.GET,
                        tokenService.createAuthorizationHeaders(), BrokerLocationDTO[].class).getBody();
        if (brokerLocationDTOS != null) {
            addLocationsToDB(brokerLocationDTOS);
        }
        return brokerLocationDTOS;
    }

    private void addLocationsToDB(BrokerLocationDTO[] brokerLocationDTOS) {
        List<LocationEntity> locationEntityList = new ArrayList<>();
        for (BrokerLocationDTO brokerLocationDTO : brokerLocationDTOS) {
            LocationEntity location = modelMapper.map(brokerLocationDTO, LocationEntity.class);
            if (checkIfLocationExistsInDb(location)) {
                locationEntityList.add(location);
            }
        }
        locationRepository.saveAllAndFlush(locationEntityList);
    }

    private boolean checkIfLocationExistsInDb(LocationEntity location) {
        List<LocationEntity> locationRepositoryAll = locationRepository.findAll();
        LocationEntity locationFromDb = locationRepositoryAll.stream()
                .filter(loc -> location.getType() == loc.getId())
                .findFirst()
                .orElse(null);
        return locationFromDb == null;
    }

    public Page<LocationModel> getLocationsFromDb(Pageable pageable) {
        return locationRepository.findAll(pageable).map(location -> modelMapper.map(location, LocationModel.class));
    }
}
