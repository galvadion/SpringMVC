package com.servicesImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GenericDao;
import com.dao.StatusBetweenDatesDao;
import com.dao.VehicleDao;
import com.dao.BookedDao;
import com.dao.BranchOfficeDao;
import com.dao.ExtrasDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
import com.entities.Extras;
import com.entities.Model;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.BookingModel;
import com.services.BookedService;

@Service
@PropertySource("classpath:config.properties")
public class BookedServiceImpl extends GenericServiceImpl<Booked, Integer> implements BookedService {

	private BookedDao bookedDao;
	private VehicleDao vehicleDao;
	private BranchOfficeDao branchOfficeDao;
	private StatusBetweenDatesDao statusBetweenDao;
	private ExtrasDao extrasDao;
	
	public BookedServiceImpl(){
		
	}
	
	@Autowired
	Environment env;

	
    @Autowired
    public BookedServiceImpl(
            @Qualifier("bookedDaoImpl") GenericDao<Booked, Integer> genericDao,
            @Qualifier("branchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao3,
            @Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao2,
            @Qualifier("statusBetweenDatesDaoImpl") GenericDao<StatusBetweenDates, Integer> genericDao4,
            @Qualifier("extrasDaoImpl") GenericDao<Extras,Integer> genericDao5) {
        super(genericDao);
        this.bookedDao = (BookedDao) genericDao;
        this.vehicleDao= (VehicleDao) genericDao2;
        this.branchOfficeDao= (BranchOfficeDao) genericDao3;
        this.statusBetweenDao = (StatusBetweenDatesDao) genericDao4;
        this.extrasDao =(ExtrasDao) genericDao5;
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public void registerBook(BookingModel model,Client client, String transactionId, String payerId, List<Extras> extras) {
		Vehicle vehicle=vehicleDao.getVehiculeAvailable(model);
		BranchOffice originBO=branchOfficeDao.getById(model.getOriginBranchOfficeId());
		BranchOffice finalBO=branchOfficeDao.getById(model.getEndBranchOfficeId());
		Booked booked=new Booked();
		booked.setBeginbookedDate(model.getStartDate());
		booked.setLastbookedDate(model.getEndDate());
		booked.setVehicle(vehicle);
		booked.setWithFullTank(model.isWithFullTank());
		booked.setWithInsurance(model.isWithInsurance());
		booked.setTransactionDate(LocalDate.now());
		booked.setOriginOffice(originBO);
		booked.setEndOffice(finalBO);
		booked.setTransactionNr(transactionId);
		booked.setPayerId(payerId);
		Model vehiculeModel=vehicle.getModel();
		float gpsByDay=0,insuranceByDay=0;
		if(model.isWithInsurance()){
			insuranceByDay=vehiculeModel.getInsurance();
		}
		float initialAmount = vehiculeModel.getCategory().getBasePrice() + gpsByDay + insuranceByDay;
		for(Extras extra : extras){
			initialAmount += extra.getPrice();
		}
		long days = model.getStartDate().until(model.getEndDate(), ChronoUnit.DAYS);
		if (days > 1) {
			initialAmount = (float) ((initialAmount * days) * (0.8));
		}
		if (model.isWithFullTank()) {
			initialAmount += vehiculeModel.getFullTank();
		}
		booked.setExtrasList(extras);
		booked.setInitialAmount(initialAmount);
		booked.setClient(client);
		vehicle.setBranchOffice(finalBO);
		for(Extras extra : extras){
			extra.getUsedIn().add(booked);
			extrasDao.saveOrUpdate(extra);
		}
		//Missing booked set client waiting for Oauth implementation
		bookedDao.saveOrUpdate(booked);
		statusBetweenDao.editStatusAtBooking(vehicle, model.getStartDate(), model.getEndDate(), finalBO,originBO);
		
	}

	public Booked getBookedByClient(Client user) {
		
		return bookedDao.getBookedByClient(user);
	}

	@Override
	public List<Booked> getBookedByDayAndOffice(BranchOffice branch, LocalDate date) {
		// TODO Auto-generated method stub
		return bookedDao.getDelivered(branch,date);
	}

	@Override
	public List<Booked> getReturnedToday(BranchOffice branch, LocalDate date) {
		// TODO Auto-generated method stub
		return bookedDao.getReturned(branch,date);
	}

	@Override
	public List<Booked> getReturnedToday(LocalDate date) {
		// TODO Auto-generated method stub
		return bookedDao.getReturned(date);
	}
	
	@Override
	public List<Booked> getDeliveredByDay(LocalDate date) {
		// TODO Auto-generated method stub
		return bookedDao.getDelivered(date);
	}

	@Override
	public List<Booked> getByClient(Client client) {
		// TODO Auto-generated method stub
		return bookedDao.getBookedByClientList(client);
	}

}
