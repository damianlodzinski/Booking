package com.example.Booking.service;

import com.example.Booking.entity.AdditionEntity;
import com.example.Booking.entity.EquipmentEntity;
import com.example.Booking.model.AdditionModel;
import com.example.Booking.model.api.BrokerRentAdditionDTO;
import com.example.Booking.repository.AdditionRepository;
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
public class ExtraService {
    private final RestTemplate restTemplate;
    private final TokenService tokenService;
    private final ModelMapper modelMapper;
    private final AdditionRepository additionRepository;
    private final EquipmentsService equipmentsService;
    @Value("${api.main.url}")
    private String apiMainUrl;
    @Value("${api.extrasAndEquipments.url}")
    private String apiExtrasAndEquipments;

    @Scheduled(cron = "0 0 */4 * * *")
    public BrokerRentAdditionDTO[] getExtrasFromApi() {
        BrokerRentAdditionDTO[] rentAdditionDTOS =
                restTemplate.exchange(apiMainUrl + apiExtrasAndEquipments, HttpMethod.GET,
                        tokenService.createAuthorizationHeaders(), BrokerRentAdditionDTO[].class).getBody();
        addExtrasToDb(rentAdditionDTOS);
        return rentAdditionDTOS;
    }

    private void addExtrasToDb(BrokerRentAdditionDTO[] rentAdditionDTOS) {
        List<EquipmentEntity> equipmentEntities = new ArrayList<>();
        List<AdditionEntity> additionEntities = new ArrayList<>();
        for (BrokerRentAdditionDTO rentAdditionDTO : rentAdditionDTOS) {
            if (rentAdditionDTO.getType().startsWith("A")) {
                AdditionEntity addition = modelMapper.map(rentAdditionDTO, AdditionEntity.class);
                if (checkIfAdditionExistsInDb(addition)) {
                    additionEntities.add(addition);
                }
            } else if (rentAdditionDTO.getType().startsWith("E")) {
                EquipmentEntity equipment = modelMapper.map(rentAdditionDTO, EquipmentEntity.class);
                equipmentEntities.add(equipment);
            }
        }
        additionRepository.saveAllAndFlush(additionEntities);
        equipmentsService.addEquipmentsToDb(equipmentEntities);
    }

    private boolean checkIfAdditionExistsInDb(AdditionEntity addition) {
        List<AdditionEntity> additionRepositoryAll = additionRepository.findAll();
        AdditionEntity additionFromDb = additionRepositoryAll.stream()
                .filter(add -> addition.getType().equals(add.getType()))
                .findFirst()
                .orElse(null);
        return additionFromDb == null;
    }

    public Page<AdditionModel> getExtrasFromDb(Pageable pageable) {
        return additionRepository.findAll(pageable).map(
                additionEntity -> modelMapper.map(additionEntity, AdditionModel.class));
    }
}
