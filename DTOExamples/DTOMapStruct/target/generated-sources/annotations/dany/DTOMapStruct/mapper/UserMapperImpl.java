package dany.DTOMapStruct.mapper;

import dany.DTOMapStruct.model.UserEntity;
import dany.DTOMapStruct.model.dto.reponsedto.UserResponseDTO;
import dany.DTOMapStruct.model.dto.requestdto.UserRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-04-12T23:18:04-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.6 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toEntity(UserRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.name( dto.getName() );
        userEntity.lastName( dto.getLastName() );
        userEntity.email( dto.getEmail() );
        userEntity.password( dto.getPassword() );

        return userEntity.build();
    }

    @Override
    public UserResponseDTO toDTO(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDTO.UserResponseDTOBuilder userResponseDTO = UserResponseDTO.builder();

        userResponseDTO.id( user.getId() );
        userResponseDTO.email( user.getEmail() );

        userResponseDTO.fullName( user.getName() + " " + user.getLastName() );

        return userResponseDTO.build();
    }
}
