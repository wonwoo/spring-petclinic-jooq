/*
 * This file is generated by jOOQ.
 */
package org.springframework.samples.domain;


import javax.annotation.Generated;

import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Record;
import org.jooq.UniqueKey;
import org.jooq.impl.Internal;
import org.springframework.samples.domain.tables.Owner;
import org.springframework.samples.domain.tables.Pet;
import org.springframework.samples.domain.tables.PetType;
import org.springframework.samples.domain.tables.Specialty;
import org.springframework.samples.domain.tables.Vet;
import org.springframework.samples.domain.tables.VetSpecialty;
import org.springframework.samples.domain.tables.Visit;


/**
 * A class modelling foreign key relationships and constraints of tables of 
 * the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<Record, Integer> IDENTITY_OWNER = Identities0.IDENTITY_OWNER;
    public static final Identity<Record, Integer> IDENTITY_PET = Identities0.IDENTITY_PET;
    public static final Identity<Record, Integer> IDENTITY_PET_TYPE = Identities0.IDENTITY_PET_TYPE;
    public static final Identity<Record, Integer> IDENTITY_SPECIALTY = Identities0.IDENTITY_SPECIALTY;
    public static final Identity<Record, Integer> IDENTITY_VET = Identities0.IDENTITY_VET;
    public static final Identity<Record, Integer> IDENTITY_VISIT = Identities0.IDENTITY_VISIT;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<Record> SYS_PK_10162 = UniqueKeys0.SYS_PK_10162;
    public static final UniqueKey<Record> SYS_PK_10164 = UniqueKeys0.SYS_PK_10164;
    public static final UniqueKey<Record> SYS_PK_10160 = UniqueKeys0.SYS_PK_10160;
    public static final UniqueKey<Record> SYS_PK_10145 = UniqueKeys0.SYS_PK_10145;
    public static final UniqueKey<Record> SYS_PK_10143 = UniqueKeys0.SYS_PK_10143;
    public static final UniqueKey<Record> SYS_PK_10178 = UniqueKeys0.SYS_PK_10178;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<Record, Record> FK_PET_PET_TYPE = ForeignKeys0.FK_PET_PET_TYPE;
    public static final ForeignKey<Record, Record> FK_PET_OWNERS = ForeignKeys0.FK_PET_OWNERS;
    public static final ForeignKey<Record, Record> FK_VET_SPECIALTY_VET = ForeignKeys0.FK_VET_SPECIALTY_VET;
    public static final ForeignKey<Record, Record> FK_VET_SPECIALTY_SPECIALTY = ForeignKeys0.FK_VET_SPECIALTY_SPECIALTY;
    public static final ForeignKey<Record, Record> FK_VISIT_PETS = ForeignKeys0.FK_VISIT_PETS;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 {
        public static Identity<Record, Integer> IDENTITY_OWNER = Internal.createIdentity(Owner.OWNER, Owner.OWNER.ID);
        public static Identity<Record, Integer> IDENTITY_PET = Internal.createIdentity(Pet.PET, Pet.PET.ID);
        public static Identity<Record, Integer> IDENTITY_PET_TYPE = Internal.createIdentity(PetType.PET_TYPE, PetType.PET_TYPE.ID);
        public static Identity<Record, Integer> IDENTITY_SPECIALTY = Internal.createIdentity(Specialty.SPECIALTY, Specialty.SPECIALTY.ID);
        public static Identity<Record, Integer> IDENTITY_VET = Internal.createIdentity(Vet.VET, Vet.VET.ID);
        public static Identity<Record, Integer> IDENTITY_VISIT = Internal.createIdentity(Visit.VISIT, Visit.VISIT.ID);
    }

    private static class UniqueKeys0 {
        public static final UniqueKey<Record> SYS_PK_10162 = Internal.createUniqueKey(Owner.OWNER, "SYS_PK_10162", Owner.OWNER.ID);
        public static final UniqueKey<Record> SYS_PK_10164 = Internal.createUniqueKey(Pet.PET, "SYS_PK_10164", Pet.PET.ID);
        public static final UniqueKey<Record> SYS_PK_10160 = Internal.createUniqueKey(PetType.PET_TYPE, "SYS_PK_10160", PetType.PET_TYPE.ID);
        public static final UniqueKey<Record> SYS_PK_10145 = Internal.createUniqueKey(Specialty.SPECIALTY, "SYS_PK_10145", Specialty.SPECIALTY.ID);
        public static final UniqueKey<Record> SYS_PK_10143 = Internal.createUniqueKey(Vet.VET, "SYS_PK_10143", Vet.VET.ID);
        public static final UniqueKey<Record> SYS_PK_10178 = Internal.createUniqueKey(Visit.VISIT, "SYS_PK_10178", Visit.VISIT.ID);
    }

    private static class ForeignKeys0 {
        public static final ForeignKey<Record, Record> FK_PET_PET_TYPE = Internal.createForeignKey(org.springframework.samples.domain.Keys.SYS_PK_10160, Pet.PET, "FK_PET_PET_TYPE", Pet.PET.TYPE_ID);
        public static final ForeignKey<Record, Record> FK_PET_OWNERS = Internal.createForeignKey(org.springframework.samples.domain.Keys.SYS_PK_10162, Pet.PET, "FK_PET_OWNERS", Pet.PET.OWNER_ID);
        public static final ForeignKey<Record, Record> FK_VET_SPECIALTY_VET = Internal.createForeignKey(org.springframework.samples.domain.Keys.SYS_PK_10143, VetSpecialty.VET_SPECIALTY, "FK_VET_SPECIALTY_VET", VetSpecialty.VET_SPECIALTY.VET);
        public static final ForeignKey<Record, Record> FK_VET_SPECIALTY_SPECIALTY = Internal.createForeignKey(org.springframework.samples.domain.Keys.SYS_PK_10145, VetSpecialty.VET_SPECIALTY, "FK_VET_SPECIALTY_SPECIALTY", VetSpecialty.VET_SPECIALTY.SPECIALTY);
        public static final ForeignKey<Record, Record> FK_VISIT_PETS = Internal.createForeignKey(org.springframework.samples.domain.Keys.SYS_PK_10164, Visit.VISIT, "FK_VISIT_PETS", Visit.VISIT.PET_ID);
    }
}
