package com.servicesImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.StatusBetweenDatesDao;
import com.dao.VehiculeDao;
import com.dao.BookedDao;
import com.dao.BranchOfficeDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.entities.Vehicule;
import com.models.BookingModel;
import com.services.BookedService;
import com.services.StatusBetweenDatesService;

@Service
public class StatusBetweenDatesServiceImpl extends GenericServiceImpl<StatusBetweenDates, Integer> implements StatusBetweenDatesService{

	private StatusBetweenDatesDao statusBetweenDatesDao;
	private VehiculeDao vehiculeDao;
	private BranchOfficeDao branchOfficeDao;
	
	public StatusBetweenDatesServiceImpl(){
		
	}
	
	
    @Autowired
    public StatusBetweenDatesServiceImpl(
            @Qualifier("statusBetweenDatesDaoImpl") GenericDao<StatusBetweenDates, Integer> genericDao,
            @Qualifier("vehiculeDaoImpl") GenericDao<Vehicule, Integer> genericDao2,
            @Qualifier("branchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao3) {
        super(genericDao);
        this.statusBetweenDatesDao = (StatusBetweenDatesDao) genericDao;
        this.vehiculeDao = (VehiculeDao) genericDao2;
        this.branchOfficeDao = (BranchOfficeDao) genericDao3;
    }


	public void editStatusAtBooked(BookingModel model) {
		// TODO Auto-generated method stub
		statusBetweenDatesDao.editStatusAtBooking(vehiculeDao.getVehiculeAvailable(model), model.getStartDate(), model.getEndDate(), branchOfficeDao.getById(model.getOriginBranchOfficeId()));
	}


	
}
