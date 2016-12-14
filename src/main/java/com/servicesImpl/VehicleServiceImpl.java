package com.servicesImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.dao.BookedDao;
import com.dao.GenericDao;
import com.dao.StatusBetweenDatesDao;
import com.dao.VehicleDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.StatusBetweenDates;
import com.entities.Vehicle;
import com.models.BookingModel;
import com.services.VehicleService;

@Service
public class VehicleServiceImpl extends GenericServiceImpl<Vehicle, Integer> implements VehicleService {

	private VehicleDao vehicleDao;

	private StatusBetweenDatesDao statusDao;

	private BookedDao bookedDao;

	public VehicleServiceImpl() {

	}

	@Autowired
	public VehicleServiceImpl(@Qualifier("vehicleDaoImpl") GenericDao<Vehicle, Integer> genericDao,
			@Qualifier("statusBetweenDatesDaoImpl") GenericDao<StatusBetweenDates, Integer> genericDao2,
			@Qualifier("bookedDaoImpl") GenericDao<Booked, Integer> genericDao3) {
		super(genericDao);
		this.vehicleDao = (VehicleDao) genericDao;
		this.statusDao = (StatusBetweenDatesDao) genericDao2;
		this.bookedDao = (BookedDao) genericDao3;
	}

	public List<Vehicle> getAvailable() {
		return vehicleDao.getAvailable();
	}

	public List<Vehicle> getPickedUpToday(BranchOffice bo) {
		// TODO Auto-generated method stub
		return vehicleDao.getPickedUpToday(bo);
	}

	public List<Vehicle> getReturnedToday(BranchOffice bo) {
		// TODO Auto-generated method stub
		return vehicleDao.getReturnedToday(bo);
	}

	public void abortNewEvents(Vehicle vehicle,LocalDate firstDate) {
		List<StatusBetweenDates> nextStatus = statusDao.getNextStatus(vehicle,firstDate);
		for (StatusBetweenDates status : nextStatus) {
			statusDao.delete(status);
		}
		List<Booked> nextBooked = bookedDao.getNextBooked(vehicle,firstDate);
		for (Booked book : nextBooked) {
			BookingModel bm = new BookingModel();
			bm.setEndBranchOfficeId(book.getEndOffice().getId());
			bm.setEndDate(book.getLastbookedDate());
			bm.setIdModel(vehicle.getModel().getId());
			bm.setOriginBranchOfficeId(book.getOriginOffice().getId());
			bm.setStartDate(book.getBeginbookedDate());
			book.setVehicle(vehicleDao.getVehiculeAvailable(bm));
			bookedDao.saveOrUpdate(book);
		}
	}

}
