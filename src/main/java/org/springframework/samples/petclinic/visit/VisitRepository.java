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
package org.springframework.samples.petclinic.visit;

import static org.springframework.samples.domain.tables.Visit.VISIT;

import java.util.List;
import org.jooq.DSLContext;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


/**
 * Repository class for <code>Visit</code> domain objects All method names are compliant with Spring Data naming conventions so this interface can easily be
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
public class VisitRepository {

    private final DSLContext dsl;

    public VisitRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    /**
     * Save a <code>Visit</code> to the data store, either inserting or updating it.
     *
     * @param visit the <code>Visit</code> to save
     */
    @Transactional
    public void save(Visit visit) throws DataAccessException {
        this.dsl.insertInto(VISIT)
            .columns(VISIT.DATE, VISIT.DESCRIPTION, VISIT.PET_ID)
            .values(visit.getDate(), visit.getDescription(), visit.getPetId())
            .execute();
    }

    public List<Visit> findByPetId(Integer petId) {
        return this.dsl.select().from(VISIT)
            .where(VISIT.PET_ID.eq(petId))
            .fetch()
            .into(Visit.class);
    }
}
