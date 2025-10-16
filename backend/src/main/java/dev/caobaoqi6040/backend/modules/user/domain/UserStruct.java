package dev.caobaoqi6040.backend.modules.user.domain;

import dev.caobaoqi6040.backend.modules.user.domain.response.UserInfoVo;
import dev.caobaoqi6040.backend.modules.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserStruct {
	UserInfoVo user2UserInfo(User user);
}
