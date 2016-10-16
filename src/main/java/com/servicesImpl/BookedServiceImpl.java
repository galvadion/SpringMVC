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
import com.dao.VehiculeDao;
import com.dao.BookedDao;
import com.dao.BranchOfficeDao;
import com.entities.Booked;
import com.entities.BranchOffice;
import com.entities.Model;
import com.entities.Vehicule;
import com.models.BookingModel;
import com.services.BookedService;

@Service
@PropertySource("classpath:config.properties")
public class BookedServiceImpl extends GenericServiceImpl<Booked, Integer> implements BookedService{

	private BookedDao BookedDao;
	private VehiculeDao vehiculeDao;
	private BranchOfficeDao branchOfficeDao;
	
	public BookedServiceImpl(){
		
	}
	
	@Autowired
	Environment env;

	
    @Autowired
    public BookedServiceImpl(
            @Qualifier("bookedDaoImpl") GenericDao<Booked, Integer> genericDao,
            @Qualifier("branchOfficeDaoImpl") GenericDao<BranchOffice, Integer> genericDao3,
            @Qualifier("vehiculeDaoImpl") GenericDao<Vehicule, Integer> genericDao2) {
        super(genericDao);
        this.BookedDao = (BookedDao) genericDao;
        this.vehiculeDao= (VehiculeDao) genericDao2;
        this.branchOfficeDao= (BranchOfficeDao) genericDao3;
    }

	public void registerBoot(BookingModel model) {
		Vehicule vehicule=vehiculeDao.getVehiculeAvailable(model);
		BranchOffice originBO=branchOfficeDao.getById(model.getOriginBranchOfficeId());
		BranchOffice finalBO=branchOfficeDao.getById(model.getEndBranchOfficeId());
		Booked booked=new Booked();
		booked.setBeginbookedDate(model.getStartDate());
		booked.setLastbookedDate(model.getEndDate());
		booked.setVehicule(vehicule);
		booked.setWithGps(model.isWithGps());
		booked.setWithFullTank(model.isWithFullTank());
		booked.setWithInsurance(model.isWithInsurance());
		booked.setTransactionDate(LocalDate.now());
		booked.setOriginOffice(originBO);
		booked.setEndOffice(finalBO);
		Model vehiculeModel=vehicule.getModel();
		float gpsByDay=0,insuranceByDay=0;
		if(model.isWithGps()){
			gpsByDay=Float.valueOf(env.getProperty("gps"));
		}if(model.isWithInsurance()){
			insuranceByDay=vehiculeModel.getInsurance();
		}
		float initialAmount=vehiculeModel.getCategory().getBasePrice()+gpsByDay+insuranceByDay;
		long days=model.getStartDate().until(model.getEndDate(), ChronoUnit.DAYS);
		if(days>1){
			initialAmount = (float) ((initialAmount*days)*(0.8));
		}
		if(model.isWithFullTank()){
			initialAmount += vehiculeModel.getFullTank();
		}
		booked.setInitialAmount(initialAmount);
		System.out.println();
		
	}

	
}
