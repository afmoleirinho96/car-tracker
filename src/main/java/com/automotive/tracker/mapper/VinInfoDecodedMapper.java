package com.automotive.tracker.mapper;

import com.automotive.tracker.to.providers.VinInfoDecode;
import com.automotive.tracker.to.rest.vindecode.VinInfoDecodeDto;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface VinInfoDecodedMapper {

    VinInfoDecodeDto map(VinInfoDecode vinInfoDecode);
}
