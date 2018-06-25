/*
 * Hibernate, Relational Persistence for Idiomatic Java
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later.
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */

package org.jboss.perf.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Animal {

    private Long id;

    private float bodyWeight;
    private Set<Animal> offspring;
    private Animal mother;
    private Animal father;
    private String description;
    private Zoo zoo;
    private String serialNumber;
    private Map attributes;

    public Animal() {
    }

    public Animal(String description, float bodyWeight) {
        this.description = description;
        this.bodyWeight = bodyWeight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(float bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Set getOffspring() {
        return offspring;
    }

    public void setOffspring(Set<Animal> offspring) {
        this.offspring = offspring;
    }

    public void addOffspring(Animal offspring) {
        if ( this.offspring == null ) {
            this.offspring = new HashSet<>();
        }

        this.offspring.add( offspring );
    }

    public Animal getMother() {
        return mother;
    }

    public void setMother(Animal mother) {
        this.mother = mother;
    }

    public Animal getFather() {
        return father;
    }

    public void setFather(Animal father) {
        this.father = father;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Zoo getZoo() {
        return zoo;
    }

    public void setZoo(Zoo zoo) {
        this.zoo = zoo;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Map getAttributes() {
        if (attributes == null)
            attributes = new HashMap();
        return attributes;
    }
    public void setAttributes(Map attributes) {
        this.attributes = attributes;
    }

    public Object getValueOfCustomField(String name) {
        return getAttributes().get(name);
    }

    public void setValueOfCustomField(String name, Object value) {
        getAttributes().put(name, value);
    }
}
