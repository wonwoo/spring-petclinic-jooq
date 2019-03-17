/*
 * This file is generated by jOOQ.
 */
package org.springframework.samples.domain.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;
import org.springframework.samples.domain.Indexes;
import org.springframework.samples.domain.Keys;
import org.springframework.samples.domain.Public;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VetSpecialty extends TableImpl<Record> {

    private static final long serialVersionUID = 604277513;

    /**
     * The reference instance of <code>PUBLIC.VET_SPECIALTY</code>
     */
    public static final VetSpecialty VET_SPECIALTY = new VetSpecialty();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.VET_SPECIALTY.VET</code>.
     */
    public final TableField<Record, Integer> VET = createField("VET", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.VET_SPECIALTY.SPECIALTY</code>.
     */
    public final TableField<Record, Integer> SPECIALTY = createField("SPECIALTY", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.VET_SPECIALTY</code> table reference
     */
    public VetSpecialty() {
        this(DSL.name("VET_SPECIALTY"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.VET_SPECIALTY</code> table reference
     */
    public VetSpecialty(String alias) {
        this(DSL.name(alias), VET_SPECIALTY);
    }

    /**
     * Create an aliased <code>PUBLIC.VET_SPECIALTY</code> table reference
     */
    public VetSpecialty(Name alias) {
        this(alias, VET_SPECIALTY);
    }

    private VetSpecialty(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private VetSpecialty(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> VetSpecialty(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, VET_SPECIALTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SYS_IDX_FK_VET_SPECIALTY_SPECIALTY_10157, Indexes.SYS_IDX_FK_VET_SPECIALTY_VET_10153);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.FK_VET_SPECIALTY_VET, Keys.FK_VET_SPECIALTY_SPECIALTY);
    }

    public Vet vet() {
        return new Vet(this, Keys.FK_VET_SPECIALTY_VET);
    }

    public Specialty specialty() {
        return new Specialty(this, Keys.FK_VET_SPECIALTY_SPECIALTY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VetSpecialty as(String alias) {
        return new VetSpecialty(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public VetSpecialty as(Name alias) {
        return new VetSpecialty(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public VetSpecialty rename(String name) {
        return new VetSpecialty(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public VetSpecialty rename(Name name) {
        return new VetSpecialty(name, null);
    }
}
