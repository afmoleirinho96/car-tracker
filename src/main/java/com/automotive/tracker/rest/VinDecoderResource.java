package com.automotive.tracker.rest;

import com.automotive.tracker.exceptions.VehicleNotFoundException;
import com.automotive.tracker.services.VinDecoderService;
import com.automotive.tracker.to.rest.vindecode.VinDecodeDto;
import com.automotive.tracker.to.rest.vindecode.VinInfoDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("vehicles/{vehicleId}")
public class VinDecoderResource {

    private final VinDecoderService vinDecoderService;

    public VinDecoderResource(VinDecoderService vinDecoderService) {
        this.vinDecoderService = vinDecoderService;
    }

    @GetMapping(path = "vin-info")
    public VinInfoDto getVinInfo(@PathVariable String vehicleId) {
        VinInfoDto vinInfo = vinDecoderService.getVinInfo(vehicleId);

        if (vinInfo.getVinInfo() == null) {
            throw VehicleNotFoundException.vinDecodeNotFound(vehicleId);
        }

        return vinInfo;
    }

    @GetMapping(path = "vin-info-decoded", produces = "application/json")
    public VinDecodeDto getVinInfoDecoded(@PathVariable String vehicleId) {
        VinDecodeDto vinDecode = vinDecoderService.decodeVinInfo(vehicleId);

        if (vinDecode.getVinDecode() == null) {
            throw VehicleNotFoundException.vinDecodeNotFound(vehicleId);
        }

        return vinDecoderService.decodeVinInfo(vehicleId);
    }

}
