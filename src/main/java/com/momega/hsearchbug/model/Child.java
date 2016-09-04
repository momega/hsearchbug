package com.momega.hsearchbug.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;

@Entity
@Table(name = "BUG_CHILD")
public class Child {

	@Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SequenceGeneratorId", sequenceName = "BUG_CHILD_S", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceGeneratorId")
    private Long id;
	 
	@Column(name="VALUE")
	@Field
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	} 
	
}
