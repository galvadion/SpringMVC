package com.daoImpl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RentDao;
import com.entities.BranchOffice;
import com.entities.Rent;

@Repository
@Transactional
public class RentDaoImpl extends GenericDaoImpl<Rent, Integer> implements RentDao {

	public RentDaoImpl(){
		super(null);
	}
	
	public RentDaoImpl(Class<Rent> entityClass) {
		super(entityClass);
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public List<Rent> getBetweenDates(LocalDate initialDate, LocalDate finalDate) {
		Query query=currentSession().createQuery("from Rent where deliveryDate>:initialDate and returnDate<:finalDate");
		query.setParameter("initialDate", initialDate);
		query.setParameter("finalDate", finalDate);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Rent> getReturnedToday(BranchOffice branch, LocalDate day) {
		Query query=currentSession().createQuery("Select r from Rent r join r.booked b where r.returnDate = :initialDate and b.endOffice =:office");
		query.setParameter("initialDate", day);
		query.setParameter("office", branch);

		return query.getResultList();
	}
	
}
