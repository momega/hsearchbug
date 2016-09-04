/**
 * 
 */
package com.momega.hsearchbug.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

/**
 * @author martin
 *
 */
@Entity
@Table(name = "BUG_PARENT")
@Indexed(index = "testingindex")
public class Parent {

	@Id
    @Column(name = "ID")
    @SequenceGenerator(name = "SequenceGeneratorId", sequenceName = "BUG_PARENT_S", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SequenceGeneratorId")
    private Long id;
	 
	@Column(name="DATA")
	@Field
	private String data; 
	
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "PARENT_ID")
	@IndexedEmbedded
    private List<Child> attributes = new ArrayList<Child>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Child> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Child> attributes) {
		this.attributes = attributes;
	}
	
	
}
