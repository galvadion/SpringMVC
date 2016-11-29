package com.servicesImpl;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Client;
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

	public BookedServiceImpl() {

	}

	@Autowired
	Environment env;

	@Autowired
	public BookedServiceImpl(@Qualifier("bookedDaoImpl") GenericDao<Booked, Integer> genericDao,
			@Qualifier("branchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao3,
			@Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao2,
			@Qualifier("statusBetweenDatesDaoImpl") GenericDao<StatusBetweenDates, Integer> genericDao4) {
		super(genericDao);
		this.bookedDao = (BookedDao) genericDao;
		this.vehicleDao = (VehicleDao) genericDao2;
		this.branchOfficeDao = (BranchOfficeDao) genericDao3;
		this.statusBetweenDao = (StatusBetweenDatesDao) genericDao4;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void registerBoot(BookingModel model, Client client) {
		Vehicle vehicle = vehicleDao.getVehiculeAvailable(model);
		BranchOffice originBO = branchOfficeDao.getById(model.getOriginBranchOfficeId());
		BranchOffice finalBO = branchOfficeDao.getById(model.getEndBranchOfficeId());
		Booked booked = new Booked();
		booked.setBeginbookedDate(model.getStartDate());
		booked.setLastbookedDate(model.getEndDate());
		booked.setVehicle(vehicle);
		booked.setWithGps(model.isWithGps());
		booked.setWithFullTank(model.isWithFullTank());
		booked.setWithInsurance(model.isWithInsurance());
		booked.setTransactionDate(LocalDate.now());
		booked.setOriginOffice(originBO);
		booked.setEndOffice(finalBO);
		Model vehiculeModel = vehicle.getModel();
		float gpsByDay = 0, insuranceByDay = 0;
		if (model.isWithGps()) {
			gpsByDay = Float.valueOf(env.getProperty("gps"));
		}
		if (model.isWithInsurance()) {
			insuranceByDay = vehiculeModel.getInsurance();
		}
		float initialAmount = vehiculeModel.getCategory().getBasePrice() + gpsByDay + insuranceByDay;
		long days = model.getStartDate().until(model.getEndDate(), ChronoUnit.DAYS);
		if (days > 1) {
			initialAmount = (float) ((initialAmount * days) * (0.8));
		}
		if (model.isWithFullTank()) {
			initialAmount += vehiculeModel.getFullTank();
		}
		booked.setInitialAmount(initialAmount);
		System.out.println(client);
		booked.setClient(client);
		// Missing booked set client waiting for Oauth implementation
		// missing data related to the paypal callback
		bookedDao.saveOrUpdate(booked);
		statusBetweenDao.editStatusAtBooking(vehicle, model.getStartDate(), model.getEndDate(), finalBO);

	}

	public Booked getBookedByClient(Client user) {

		return bookedDao.getBookedByClient(user);
	}

}
