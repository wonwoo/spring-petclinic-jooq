/*
 * This file is generated by jOOQ.
 */
package org.springframework.samples.domain.tables;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
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
public class Visit extends TableImpl<Record> {

    private static final long serialVersionUID = 306672627;

    /**
     * The reference instance of <code>PUBLIC.VISIT</code>
     */
    public static final Visit VISIT = new Visit();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.VISIT.ID</code>.
     */
    public final TableField<Record, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.VISIT.PET_ID</code>.
     */
    public final TableField<Record, Integer> PET_ID = createField("PET_ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.VISIT.DATE</code>.
     */
    public final TableField<Record, LocalDate> DATE = createField("DATE", org.jooq.impl.SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>PUBLIC.VISIT.DESCRIPTION</code>.
     */
    public final TableField<Record, String> DESCRIPTION = createField("DESCRIPTION", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * Create a <code>PUBLIC.VISIT</code> table reference
     */
    public Visit() {
        this(DSL.name("VISIT"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.VISIT</code> table reference
     */
    public Visit(String alias) {
        this(DSL.name(alias), VISIT);
    }

    /**
     * Create an aliased <code>PUBLIC.VISIT</code> table reference
     */
    public Visit(Name alias) {
        this(alias, VISIT);
    }

    private Visit(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Visit(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Visit(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, VISIT);
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
        return Arrays.<Index>asList(Indexes.SYS_IDX_FK_VISIT_PETS_10183, Indexes.SYS_IDX_SYS_PK_10178_10180, Indexes.VISIT_PET_ID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<Record, Integer> getIdentity() {
        return Keys.IDENTITY_VISIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10178;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.SYS_PK_10178);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<Record, ?>> getReferences() {
        return Arrays.<ForeignKey<Record, ?>>asList(Keys.FK_VISIT_PETS);
    }

    public Pet pet() {
        return new Pet(this, Keys.FK_VISIT_PETS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Visit as(String alias) {
        return new Visit(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Visit as(Name alias) {
        return new Visit(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Visit rename(String name) {
        return new Visit(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Visit rename(Name name) {
        return new Visit(name, null);
    }
}
