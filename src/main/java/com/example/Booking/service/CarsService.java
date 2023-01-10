package com.example.Booking.service;

import com.example.Booking.entity.CarEntity;
import com.example.Booking.model.CarModel;
import com.example.Booking.model.api.BrokerCarModelConfigurationDTO;
import com.example.Booking.model.api.BrokerCarModelDTO;
import com.example.Booking.repository.CarRepository;
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
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarsService {
    private final RestTemplate restTemplate;
    private final TokenService tokenService;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    @Value("${api.main.url}")
    private String apiMainUrl;
    @Value("${api.carModels.url}")
    private String apiCarModels;
    @Value("${api.carModelConfigurations.url}")
    private String apiCarModelConfigurations;

    @Scheduled(cron = "@hourly")
    public BrokerCarModelDTO[] getCarsFromApi() {
        BrokerCarModelDTO[] carModelDTOS =
                restTemplate.exchange(apiMainUrl + apiCarModels, HttpMethod.GET,
                        tokenService.createAuthorizationHeaders(), BrokerCarModelDTO[].class).getBody();
        BrokerCarModelConfigurationDTO[] carModelConfigurationDTOS =
                restTemplate.exchange(apiMainUrl + apiCarModelConfigurations, HttpMethod.GET,
                        tokenService.createAuthorizationHeaders(), BrokerCarModelConfigurationDTO[].class).getBody();
        addCarsToDb(carModelDTOS, carModelConfigurationDTOS);
        return carModelDTOS;
    }

    private void addCarsToDb(BrokerCarModelDTO[] carModelDTOS,
                             BrokerCarModelConfigurationDTO[] carModelConfigurationDTOS) {
        List<CarEntity> carEntities = new ArrayList<>();
        for (BrokerCarModelDTO carModel : carModelDTOS) {
            BrokerCarModelConfigurationDTO modelConfiguration = Arrays.stream(carModelConfigurationDTOS)
                    .toList()
                    .stream()
                    .filter(brokerCarModelConfig -> carModel.getVehicleClass().equals(brokerCarModelConfig.getCarClassAcriss()))
                    .findFirst()
                    .orElse(null);
            if (modelConfiguration != null) {
                CarEntity car = new CarEntity(carModel.getVehicleClassAlias(), carModel.getVehicleClass(),
                        carModel.getExampleCarPhoto(), modelConfiguration.getFullModelName(),
                        modelConfiguration.getFuelType(), modelConfiguration.getMaxPassengersAmount());
                if (checkIfCarExistsInDb(car)) {
                    carEntities.add(car);
                }
            }
        }
        carRepository.saveAllAndFlush(carEntities);
    }

    private boolean checkIfCarExistsInDb(CarEntity car) {
        List<CarEntity> carRepositoryAll = carRepository.findAll();
        CarEntity carFromDb = carRepositoryAll.stream()
                .filter(carE -> car.getCarClassAlias().equals(carE.getCarClassAlias()))
                .findFirst()
                .orElse(null);
        return carFromDb == null;
    }

    public Page<CarModel> getCarsFromDb(Pageable pageable) {
        return carRepository.findAll(pageable).map(car -> modelMapper.map(car, CarModel.class));
    }
}
