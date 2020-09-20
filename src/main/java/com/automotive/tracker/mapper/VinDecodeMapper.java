package com.automotive.tracker.mapper;

import com.automotive.tracker.to.providers.Balance;
import com.automotive.tracker.to.providers.VinDecode;
import com.automotive.tracker.to.rest.vindecode.VinDecodeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    config = BaseMapperConfig.class,
    uses = {BalanceMapper.class}
)
public interface VinDecodeMapper {

    @Mapping(target = "vinDecode", source = "vin.decode")
    @Mapping(target = "apiRequestsLeft", source ="balance.apiRequestsLeft")
    VinDecodeDto map(VinDecode vin, Balance balance);

    default Integer getVinDecodeBalance(Balance balance) {
        if (balance == null) {
            return null;
        }
        return balance.getApiRequestsLeft() != null ? balance.getApiRequestsLeft() : null;
    }


}
