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
package org.springframework.samples.petclinic.vet;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static org.springframework.samples.domain.Tables.SPECIALTY;
import static org.springframework.samples.domain.Tables.VET;
import static org.springframework.samples.domain.Tables.VET_SPECIALTY;

import java.util.List;
import java.util.Set;
import org.jooq.DSLContext;
import org.jooq.Record3;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repository class for <code>Vet</code> domain objects All method names are compliant with Spring Data naming conventions so this interface can easily be
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
public class VetRepository {

    private final DSLContext dsl;

    public VetRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Retrieve all <code>Vet</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Vet</code>s
     */
    @Cacheable("vets")
    public List<Vet> findAll() {
        return this.dsl.select().from(VET)
            .leftJoin(VET_SPECIALTY)
            .on(VET.ID.eq(VET_SPECIALTY.VET))
            .leftJoin(SPECIALTY)
            .on(SPECIALTY.ID.eq(VET_SPECIALTY.SPECIALTY))
            .orderBy(VET.ID)
            .fetch()
            .intoGroups(VET)
            .values()
            .stream()
            .map(records -> {
                final Record3<Integer, String, String> record3 = records.into(VET.ID, VET.FIRST_NAME, VET.LAST_NAME).get(0);
                Set<Specialty> specialties = records.sortAsc(VET.ID).into(Specialty.class)
                    .stream()
                    .filter(specialty -> specialty.getId() != null)
                    .collect(toSet());
                return new Vet(record3.value1(), record3.value2(), record3.value3(), specialties);
            }).collect(toList());
    }

}
