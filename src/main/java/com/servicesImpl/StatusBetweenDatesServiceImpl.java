package com.servicesImpl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.GenericDao;
import com.dao.StatusBetweenDatesDao;
import com.dao.VehicleDao;
import com.dao.BranchOfficeDao;
import com.entities.BranchOffice;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.BookingModel;
import com.services.StatusBetweenDatesService;

@Service
public class StatusBetweenDatesServiceImpl extends GenericServiceImpl<StatusBetweenDates, Integer> implements StatusBetweenDatesService{

	private StatusBetweenDatesDao statusBetweenDatesDao;
	private VehicleDao vehicleDao;
	private BranchOfficeDao branchOfficeDao;
	
	public StatusBetweenDatesServiceImpl(){
		
	}
	
	
    @Autowired
    public StatusBetweenDatesServiceImpl(
            @Qualifier("statusBetweenDatesDaoImpl") GenericDao<StatusBetweenDates, Integer> genericDao,
            @Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao2,
            @Qualifier("branchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao3) {
        super(genericDao);
        this.statusBetweenDatesDao = (StatusBetweenDatesDao) genericDao;
        this.vehicleDao = (VehicleDao) genericDao2;
        this.branchOfficeDao = (BranchOfficeDao) genericDao3;
    }


	public void editStatusAtBooked(BookingModel model) {
		// TODO Auto-generated method stub
		statusBetweenDatesDao.editStatusAtBooking(vehicleDao.getVehiculeAvailable(model), model.getStartDate(), model.getEndDate(), branchOfficeDao.getById(model.getOriginBranchOfficeId()));
	}


	public StatusBetweenDates getCurrentStatus(Vehicle vehicle, LocalDate firstDate) {
		
		return statusBetweenDatesDao.getActualStatus(vehicle, firstDate);
	}


	
}
