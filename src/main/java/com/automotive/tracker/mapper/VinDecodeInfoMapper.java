package com.automotive.tracker.mapper;

import com.automotive.tracker.to.providers.VinDecodeInfo;
import com.automotive.tracker.to.rest.vindecode.VinInfoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
    config = BaseMapperConfig.class
)
public interface VinDecodeInfoMapper {

    @Mapping(target = "vinInfo", source = "decode")
    VinInfoDto map(VinDecodeInfo info);

}
