/*
 * This file is generated by jOOQ.
 */
package org.springframework.samples.domain.tables;


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
public class Owner extends TableImpl<Record> {

    private static final long serialVersionUID = 1367259554;

    /**
     * The reference instance of <code>PUBLIC.OWNER</code>
     */
    public static final Owner OWNER = new Owner();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<Record> getRecordType() {
        return Record.class;
    }

    /**
     * The column <code>PUBLIC.OWNER.ID</code>.
     */
    public final TableField<Record, Integer> ID = createField("ID", org.jooq.impl.SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>PUBLIC.OWNER.FIRST_NAME</code>.
     */
    public final TableField<Record, String> FIRST_NAME = createField("FIRST_NAME", org.jooq.impl.SQLDataType.VARCHAR(30), this, "");

    /**
     * The column <code>PUBLIC.OWNER.LAST_NAME</code>.
     */
    public final TableField<Record, String> LAST_NAME = createField("LAST_NAME", org.jooq.impl.SQLDataType.VARCHAR(30), this, "");

    /**
     * The column <code>PUBLIC.OWNER.ADDRESS</code>.
     */
    public final TableField<Record, String> ADDRESS = createField("ADDRESS", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>PUBLIC.OWNER.CITY</code>.
     */
    public final TableField<Record, String> CITY = createField("CITY", org.jooq.impl.SQLDataType.VARCHAR(80), this, "");

    /**
     * The column <code>PUBLIC.OWNER.TELEPHONE</code>.
     */
    public final TableField<Record, String> TELEPHONE = createField("TELEPHONE", org.jooq.impl.SQLDataType.VARCHAR(20), this, "");

    /**
     * Create a <code>PUBLIC.OWNER</code> table reference
     */
    public Owner() {
        this(DSL.name("OWNER"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.OWNER</code> table reference
     */
    public Owner(String alias) {
        this(DSL.name(alias), OWNER);
    }

    /**
     * Create an aliased <code>PUBLIC.OWNER</code> table reference
     */
    public Owner(Name alias) {
        this(alias, OWNER);
    }

    private Owner(Name alias, Table<Record> aliased) {
        this(alias, aliased, null);
    }

    private Owner(Name alias, Table<Record> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Owner(Table<O> child, ForeignKey<O, Record> key) {
        super(child, key, OWNER);
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
        return Arrays.<Index>asList(Indexes.OWNERS_LAST_NAME, Indexes.SYS_IDX_SYS_PK_10162_10163);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<Record, Integer> getIdentity() {
        return Keys.IDENTITY_OWNER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<Record> getPrimaryKey() {
        return Keys.SYS_PK_10162;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<Record>> getKeys() {
        return Arrays.<UniqueKey<Record>>asList(Keys.SYS_PK_10162);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Owner as(String alias) {
        return new Owner(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Owner as(Name alias) {
        return new Owner(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Owner rename(String name) {
        return new Owner(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Owner rename(Name name) {
        return new Owner(name, null);
    }
}
