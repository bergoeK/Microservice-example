package com.user.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Book.class)
public abstract class Book_ {

	public static volatile SingularAttribute<Book, Library> library;
	public static volatile SingularAttribute<Book, String> name;
	public static volatile SingularAttribute<Book, Long> id;
	public static volatile SingularAttribute<Book, LocalDateTime> publishedDate;
	public static volatile SetAttribute<Book, Author> authors;

	public static final String LIBRARY = "library";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PUBLISHED_DATE = "publishedDate";
	public static final String AUTHORS = "authors";

}

