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

import static java.util.stream.Collectors.toSet;
import static org.springframework.samples.domain.tables.Owner.OWNER;
import static org.springframework.samples.domain.tables.Pet.PET;
import static org.springframework.samples.domain.tables.PetType.PET_TYPE;
import static org.springframework.samples.domain.tables.Visit.VISIT;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Pet</code> domain objects All method names are compliant with Spring Data naming conventions so this interface can easily be
 * extended for Spring Data See here: http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Maciej Walkowiak
 * @author Wonwoo Lee
 */
@Repository
@Transactional(readOnly = true)
public class PetRepository {

    private final DSLContext dsl;

    public PetRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Retrieve all {@link PetType}s from the data store.
     *
     * @return a Collection of {@link PetType}s.
     */

    public List<PetType> findPetTypes() {
        return this.dsl.select().from(PET_TYPE)
            .orderBy(PET_TYPE.NAME)
            .fetchInto(PetType.class);

    }

    /**
     * Retrieve a {@link Pet} from the data store by id.
     *
     * @param id the id to search for
     * @return the {@link Pet} if found
     */
    public Pet findById(Integer id) {
        return this.dsl.select().from(PET)
            .join(OWNER)
            .on(PET.OWNER_ID.eq(OWNER.ID))
            .join(PET_TYPE)
            .on(PET.TYPE_ID.eq(PET_TYPE.ID))
            .leftJoin(VISIT)
            .on(PET.ID.eq(VISIT.PET_ID))
            .where(PET.ID.eq(id))
            .fetch()
            .intoGroups(PET)
            .values()
            .stream()
            .map(records -> {
                Record3<Integer, String, LocalDate> record3s = records.into(PET.ID, PET.NAME, PET.BIRTH_DATE).get(0);
                Owner owner = records.into(Owner.class).get(0);
                PetType petType = records.into(PetType.class).get(0);
                Set<Visit> visits = records.into(Visit.class)
                    .stream()
                    .filter(visit -> visit.getId() != null)
                    .collect(toSet());
                return new Pet(record3s.value1(), record3s.value2(), record3s.value3(), petType, owner, visits);

            }).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("not found Pet!"));
    }

    /**
     * Save a {@link Pet} to the data store, either inserting it.
     *
     * @param pet the {@link Pet} to save
     */
    @Transactional
    public void save(Pet pet) {
        this.dsl.insertInto(PET)
            .columns(PET.NAME, PET.BIRTH_DATE, PET.TYPE_ID, PET.OWNER_ID)
            .values(pet.getName(), pet.getBirthDate(), pet.getType().getId(), pet.getOwner().getId()).execute();
    }

    /**
     * Update a {@link Pet} to the data store, either updating it.
     *
     * @param pet the {@link Pet} to update
     */
    @Transactional
    public void update(Pet pet) {
        this.dsl.update(PET)
            .set(PET.NAME, pet.getName())
            .set(PET.BIRTH_DATE, pet.getBirthDate())
            .set(PET.TYPE_ID, pet.getType().getId())
            .where(PET.ID.eq(pet.getId()))
            .execute();
    }
}

