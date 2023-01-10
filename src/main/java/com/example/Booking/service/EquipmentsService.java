package com.example.Booking.service;

import com.example.Booking.entity.EquipmentEntity;
import com.example.Booking.model.EquipmentModel;
import com.example.Booking.repository.EquipmentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipmentsService {
    private final EquipmentRepository equipmentRepository;
    private final ModelMapper modelMapper;

    public Page<EquipmentModel> getEquipmentsFromDb(Pageable pageable) {
        return equipmentRepository.findAll(pageable).map(equipment -> modelMapper.map(equipment, EquipmentModel.class));
    }

    public void addEquipmentsToDb(List<EquipmentEntity> equipmentEntities) {
        List<EquipmentEntity> equipmentEntityList = new ArrayList<>();
        for (EquipmentEntity equipmentEntity : equipmentEntities) {
            if (checkIfEquipmentsExistsInDb(equipmentEntity)) {
                equipmentEntityList.add(equipmentEntity);
            }
        }
        equipmentRepository.saveAllAndFlush(equipmentEntityList);
    }

    private boolean checkIfEquipmentsExistsInDb(EquipmentEntity equipmentEntity) {
        List<EquipmentEntity> equipmentRepositoryAll = equipmentRepository.findAll();
        EquipmentEntity equipmentFromDb = equipmentRepositoryAll.stream()
                .filter(equip -> equipmentEntity.getType().equals(equip.getType()))
                .findFirst()
                .orElse(null);
        return equipmentFromDb == null;
    }
}
