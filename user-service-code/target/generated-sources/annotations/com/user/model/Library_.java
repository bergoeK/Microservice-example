package com.user.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Library.class)
public abstract class Library_ {

	public static volatile SetAttribute<Library, Book> books;
	public static volatile SingularAttribute<Library, String> name;
	public static volatile SingularAttribute<Library, Long> id;

	public static final String BOOKS = "books";
	public static final String NAME = "name";
	public static final String ID = "id";

}

