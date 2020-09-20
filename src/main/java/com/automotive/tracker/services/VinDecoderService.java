package com.automotive.tracker.services;

import com.automotive.tracker.exceptions.VehicleNotFoundException;
import com.automotive.tracker.mapper.VinDecodeInfoMapper;
import com.automotive.tracker.mapper.VinDecodeMapper;
import com.automotive.tracker.model.Vehicle;
import com.automotive.tracker.repository.VehicleRepository;
import com.automotive.tracker.services.providers.VinDecoderProvider;
import com.automotive.tracker.to.providers.VinDecodeInfo;
import com.automotive.tracker.to.rest.vindecode.VinDecodeDto;
import com.automotive.tracker.to.rest.vindecode.VinInfoDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VinDecoderService {

    private static final String decodeInfoUrl = "/decode/info/";
    private static final String decodeUrl = "/decode/";

    private final VinDecoderProvider vinDecoderProvider;
    private final VehicleRepository vehicleRepository;
    private final VinDecodeInfoMapper vinDecodeInfoMapper;
    private final VinDecodeMapper vinDecodeMapper;

    public VinDecoderService(VinDecoderProvider vinDecoderProvider, VehicleRepository vehicleRepository,
                             VinDecodeInfoMapper vinDecodeInfoMapper, VinDecodeMapper vinDecodeMapper) {
        this.vinDecoderProvider = vinDecoderProvider;
        this.vehicleRepository = vehicleRepository;
        this.vinDecodeInfoMapper = vinDecodeInfoMapper;
        this.vinDecodeMapper = vinDecodeMapper;
    }

    public VinInfoDto getVinInfo(String vehicleId) {
        String vehicleVin = fetchVehicleVin(vehicleId);

        VinDecodeInfo vinDecodeInfo = vinDecoderProvider.vinDecodeInfo(decodeInfoUrl, vehicleVin);

        return Optional.ofNullable(vinDecodeInfo)
                       .map(vinDecodeInfoMapper::map)
                       .orElseThrow(VehicleNotFoundException.supplyVinDecodeNotFound(vehicleVin));
    }

    public VinDecodeDto decodeVinInfo(String vehicleId) {
        String vehicleVin = fetchVehicleVin(vehicleId);

        return Optional.ofNullable(vinDecoderProvider.vinDecode(decodeUrl, vehicleVin))
                       .map(vinDecode -> vinDecodeMapper.map(vinDecode, vinDecode.getBalance()))
                       .orElseThrow(VehicleNotFoundException.supplyVinDecodeNotFound(vehicleVin));
    }

    private String fetchVehicleVin(String vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                                           .orElseThrow(VehicleNotFoundException.supplyVehicleNotFound(vehicleId));

        String vehicleVin = vehicle.getVin();

        if (vehicleVin == null) {
            throw VehicleNotFoundException.vehicleWithVinNotFound(vehicleVin);
        }

        return vehicleVin;
    }
}
