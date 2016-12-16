package com.Tasks;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.entities.Booked;
import com.entities.Client;
import com.entities.Employee;
import com.entities.Promotion;
import com.services.BookedService;
import com.services.UserServices;
import com.servicesImpl.MailAuxiliarService;
import com.servicesImpl.PromotionService;

public class Task {

	@Autowired
	PromotionService promoService;

	@Autowired
	UserServices userService;
	
	@Autowired
	BookedService bookedService;

	@Resource(name="MailAuxiliar")
	MailAuxiliarService mailSender;

	/*
	 * The format of Scheluded(cron is "second, minute, hour, day_of_month, month, day_of_week"
	 * (*) means match any
	 * /X means "every X"
	* ?
	 */
	@Scheduled(cron="0 40 19 * * ?")
	public void sendPromotions()
	{
		List<Promotion> promotions=promoService.getCreatedYesterday();
		List<Client> clients=userService.getClientsWithNotifications();
		if(!clients.isEmpty() && !promotions.isEmpty()){
			if(promotions.size()>1){
				String promotionText="";
				for(Promotion promo: promotions){
					promotionText +="<p>Con el código "+ promo.getPromotionCode() +", "+promo.getDescriptionText()+"</p>";
				}
				
				for(Client client:clients){
					mailSender.sendMailWithHtmlText(""
							+ "<p> Buenas "+client.getName() +"!</p><br>"
									+ "<p>Nos complace anunciarle que hemos lanzado las siguientes promociones, no se las pierda!</p><br>" +promotionText +
									"<br><p> Que esperas? Reserva tu vehiculo ahora. Utiliza estos codigos </p>"
							, client.getEmail(), "No te pierdas estas ofertas de RENTUY");
				}
			}else{
				for(Client client:clients){
					mailSender.sendMailWithHtmlText(""
							+ "<p> Buenas "+client.getName() +"!</p><br>"
							+ "<p>Tenemos una maravillosa oferta para usted, si introduce el codigo "+ promotions.get(0).getPromotionCode()+ " al momento de reservar su vehiculo,"
							+ promotions.get(0).getDescriptionText()+" </p><br>"
							+ "<p>Que esperas, no te lo pierdas!!  </p>", client.getEmail(), "No te pierdas esta oferta de RENTUY");
				}
			}
		}
	}
	
	/*
	 * The format of Scheluded(cron is "second, minute, hour, day_of_month, month, day_of_week"
	 * (*) means match any
	 * /X means "every X"
	* ?
	 */
	@Scheduled(cron="0 0 8 * * ?")
	public void sendDailyReport()
	{
		List<Employee> clients=userService.getAllEmployees();
		if(!clients.isEmpty()){
				for(Employee client:clients){
					List<Booked> bookedPick=bookedService.getBookedByDayAndOffice(client.getBranchOffice(), LocalDate.now());
					String bookedText="";
					for(Booked book:bookedPick){
						bookedText +="<p>Vehiculo : "+ book.getVehicle().getLicensePlate() +", por "+book.getClient().getName() +" "+book.getClient().getLastName()+" </p><br>";
					}
					List<Booked> returnPick=bookedService.getReturnedToday(client.getBranchOffice(), LocalDate.now());
					String bookedReturn="";
					for(Booked book:returnPick){
						bookedText +="<p>Vehiculo : "+ book.getVehicle().getLicensePlate() +", por "+book.getClient().getName() +" "+book.getClient().getLastName()+" </p><br>";
					}
					mailSender.sendMailWithHtmlText("<p>Estimado "+client.getName()+"</p><br>"
							+ "<p>Le informamos los movimientos previstos para el dia de hoy:</p><br>"
							+ "<p>Se encuentran para retirar los siguientes vehiculos :</p><br>"+bookedText+"<br>"
							+ "<p>Se espera la devolucion de los siguientes vehiculos :</p><br>"+bookedReturn,
							client.getEmail(), "Reporte diario");
				}
		}
	}
}
