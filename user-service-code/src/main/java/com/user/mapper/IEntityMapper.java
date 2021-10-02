package com.user.mapper;

import java.util.Set;

public interface IEntityMapper<D, E> {

	D toDto(E e);

	E toEntity(D d);

	Set<D> toDto(Set<E> eList);

	Set<E> toEntity(Set<D> dList);

}
