package com.nie.test.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.nie.test.bean.entity.NumberPairEntity;

/**
 * This session bean encapsulates the persistent operations for {@link NumberPairEntity}. 
 * @author lnie
 *
 */
@Stateless
public class NumberMngerBean implements NumberMngerLocal {
	@PersistenceContext(name="queuePU")
	private EntityManager em;

	/**
	 * {@inheritDoc}
	 */
	public void storeNumberPair(int number1, int number2) {
		NumberPairEntity numPair = new NumberPairEntity();
		numPair.setNumber1(number1);
		numPair.setNumber2(number2);
		em.persist(numPair);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Integer> findAll() {
		List<Integer> results = new ArrayList<Integer>();
		List<NumberPairEntity> numPairEntitys = em.createQuery(
				"SELECT e FROM NumberPairEntity e").getResultList();
		for (NumberPairEntity numPair : numPairEntitys) {
			results.add(numPair.getNumber1());
			results.add(numPair.getNumber2());
		}
		return results;
	}
}
