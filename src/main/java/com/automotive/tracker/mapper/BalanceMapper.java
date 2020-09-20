package com.automotive.tracker.mapper;

import com.automotive.tracker.to.providers.Balance;
import com.automotive.tracker.to.rest.vindecode.BalanceDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface BalanceMapper {

    BalanceDto map(Balance balance);
}
