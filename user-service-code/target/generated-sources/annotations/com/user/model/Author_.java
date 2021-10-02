package com.user.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Author.class)
public abstract class Author_ {

	public static volatile SingularAttribute<Author, String> firstName;
	public static volatile SingularAttribute<Author, String> name;
	public static volatile SingularAttribute<Author, Date> dateOfBirth;
	public static volatile SingularAttribute<Author, Long> id;

	public static final String FIRST_NAME = "firstName";
	public static final String NAME = "name";
	public static final String DATE_OF_BIRTH = "dateOfBirth";
	public static final String ID = "id";

}

