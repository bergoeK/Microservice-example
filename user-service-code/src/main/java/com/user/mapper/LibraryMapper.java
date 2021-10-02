//package com.user.mapper;
//
//import org.mapstruct.InheritInverseConfiguration;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import com.user.dto.LibraryDTO;
//import com.user.model.Library;
//
//@Mapper(componentModel = "spring")
//public interface LibraryMapper extends IEntityMapper<LibraryDTO, Library> {
//
//
//	@Mapping(source = "bookDTO", target = "books")
//	LibraryDTO toDto(Library library);
//
//	
//	@InheritInverseConfiguration
//	Library toEntity(LibraryDTO d);
//}
