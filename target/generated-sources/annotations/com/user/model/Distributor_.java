package com.user.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Distributor.class)
public abstract class Distributor_ {

	public static volatile SingularAttribute<Distributor, String> name;
	public static volatile SingularAttribute<Distributor, Adresse> adresse;
	public static volatile SingularAttribute<Distributor, Long> id;

	public static final String NAME = "name";
	public static final String ADRESSE = "adresse";
	public static final String ID = "id";

}

