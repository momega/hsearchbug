/**
 * 
 */
package com.momega.hsearchbug.service;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.momega.hsearchbug.model.Child;
import com.momega.hsearchbug.model.Parent;

/**
 * @author martin
 *
 */
@Component
public class ParentService {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void inputData() {
		Parent p = new Parent();
		p.setData("Ahoj");
		p.setAttributes(new ArrayList<Child>());
		
		Child ch1 = new Child();
		ch1.setValue("Value1");
		p.getAttributes().add(ch1);
		
		Child ch2 = new Child();
		ch2.setValue("Value2");
		p.getAttributes().add(ch2);
		
		for(int i=0; i<16; i++) {
			Child ch3 = new Child();
			ch3.setValue(null);
			p.getAttributes().add(ch3);
		}
		
		Child ch4 = new Child();
		ch4.setValue("Value4");
		p.getAttributes().add(ch4);
		
		for(int i=0; i<8; i++) {
			Child ch3 = new Child();
			ch3.setValue(null);
			p.getAttributes().add(ch3);
		}
		
		Child ch5 = new Child();
		ch5.setValue("Value5");
		p.getAttributes().add(ch5);
		
		entityManager.persist(p);
	}

}
