/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.samples.domain.Tables.PET;
import static org.springframework.samples.domain.Tables.PET_TYPE;
import static org.springframework.samples.domain.tables.Owner.OWNER;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.jooq.DSLContext;
import org.jooq.Record6;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Owner</code> domain objects All method names are compliant with Spring Data naming conventions so this interface can easily be
 * extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Wonwoo Lee
 */
@Repository
@Transactional(readOnly = true)
public class OwnerRepository {

    private final DSLContext dsl;

    public OwnerRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Retrieve {@link Owner}s from the data store by last name, returning all owners whose last name <i>starts</i> with the given name.
     *
     * @param lastName Value to search for
     * @return a Collection of matching {@link Owner}s (or an empty Collection if none found)
     */

    public Collection<Owner> findByLastName(String lastName) {
        return this.dsl.select().from(OWNER)
            .leftJoin(PET)
            .on(OWNER.ID.eq(PET.OWNER_ID))
            .where(OWNER.LAST_NAME.startsWith(lastName))
            .fetch()
            .intoGroups(OWNER)
            .values()
            .stream()
            .map(records -> {
                Record6<Integer, String, String, String, String, String> record6s = records
                    .into(OWNER.ID, OWNER.FIRST_NAME, OWNER.LAST_NAME, OWNER.ADDRESS, OWNER.CITY, OWNER.TELEPHONE).get(0);
                Set<Pet> pets = records.sortAsc(OWNER.ID).into(Pet.class)
                    .stream()
                    .filter(pet -> pet.getId() != null)
                    .collect(toSet());
                return new Owner(record6s.value1(), record6s.value2(),
                    record6s.value3(), record6s.value4(), record6s.value5(), record6s.value6(), pets);
            }).collect(toCollection(ArrayList::new));
    }

    /**
     * Retrieve an {@link Owner} from the data store by id.
     *
     * @param id the id to search for
     * @return the {@link Owner} if found
     */
    public Owner findById(Integer id) {
        return this.dsl.select().from(OWNER)
            .leftJoin(PET)
            .on(OWNER.ID.eq(PET.OWNER_ID))
            .leftJoin(PET_TYPE)
            .on(PET.TYPE_ID.eq(PET_TYPE.ID))
            .where(OWNER.ID.eq(id))
            .fetch()
            .intoGroups(OWNER)
            .values()
            .stream()
            .map(records -> {
                Record6<Integer, String, String, String, String, String> record6s = records
                    .into(OWNER.ID, OWNER.FIRST_NAME, OWNER.LAST_NAME, OWNER.ADDRESS, OWNER.CITY, OWNER.TELEPHONE).get(0);
                List<Pet> pets = records.sortAsc(OWNER.ID)
                    .stream()
                    .filter(record -> record.get(PET_TYPE.ID) != null)
                    .map(record -> new Pet(record.get(PET.ID), record.get(PET.NAME), record.get(PET.BIRTH_DATE),
                        record.into(PetType.class), null, null))
                    .collect(toList());
                return new Owner(record6s.value1(), record6s.value2(),
                    record6s.value3(), record6s.value4(), record6s.value5(), record6s.value6(), new HashSet<>(pets));
            }).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("not found Owner!"));
    }

    /**
     * Save an {@link Owner} to the data store, either inserting it.
     *
     * @param owner the {@link Owner} to save
     */
    @Transactional
    public Integer save(Owner owner) {
        return this.dsl.insertInto(OWNER)
            .columns(OWNER.FIRST_NAME, OWNER.LAST_NAME, OWNER.ADDRESS, OWNER.CITY, OWNER.TELEPHONE)
            .values(owner.getFirstName(), owner.getLastName(), owner.getAddress(), owner.getCity(), owner.getTelephone())
            .returning(OWNER.ID)
            .fetchOne()
            .get(OWNER.ID);
    }

    /**
     * Save an {@link Owner} to the data store, either updating it.
     *
     * @param owner the {@link Owner} to update
     */
    @Transactional
    public void update(Owner owner) {
        this.dsl.update(OWNER)
            .set(OWNER.FIRST_NAME, owner.getFirstName())
            .set(OWNER.LAST_NAME, owner.getLastName())
            .set(OWNER.ADDRESS, owner.getAddress())
            .set(OWNER.CITY, owner.getCity())
            .set(OWNER.TELEPHONE, owner.getTelephone())
            .where(OWNER.ID.eq(owner.getId()))
            .execute();
    }
}
