package com.user.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Adresse.class)
public abstract class Adresse_ {

	public static volatile SingularAttribute<Adresse, String> street;
	public static volatile SingularAttribute<Adresse, Long> id;
	public static volatile SingularAttribute<Adresse, Long> nummer;
	public static volatile SingularAttribute<Adresse, String> plz;

	public static final String STREET = "street";
	public static final String ID = "id";
	public static final String NUMMER = "nummer";
	public static final String PLZ = "plz";

}

