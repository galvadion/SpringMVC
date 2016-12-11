package com.Tasks;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.entities.Client;
import com.entities.Promotion;
import com.services.UserServices;
import com.servicesImpl.MailAuxiliarService;
import com.servicesImpl.PromotionService;

public class Task {

	@Autowired
	PromotionService promoService;

	@Autowired
	UserServices userService;

	@Resource(name="MailAuxiliar")
	MailAuxiliarService mailSender;

	/*
	 * The format of Scheluded(cron is "second, minute, hour, day_of_month, month, day_of_week"
	 * (*) means match any
	 * /X means "every X"
	* ?
	 */
	@Scheduled(cron="0 15 16 * * ?")
	public void sendPromotions()
	{
		List<Promotion> promotions=promoService.getCreatedToday();
		List<Client> clients=userService.getClientsWithNotifications();
		if(!clients.isEmpty() && !promotions.isEmpty()){
			if(promotions.size()>1){
				for(Client client:clients){
					mailSender.sendMailWithHtmlText("", client.getEmail(), "No te pierdas estas ofertas de RENTUY");
				}
			}else{
				for(Client client:clients){
					mailSender.sendMailWithHtmlText("<p> Buenas! "+client.getName() +"</p><br><p>Tenemos una maravillosa oferta para usted, si introduce el codigo "+ promotions.get(0).getPromotionCode()+ " al momento de reservar su vehiculo, obtendra un fabuloso descuento del "+ String.format("%.0f", promotions.get(0).getPercentage()) + "%. </p><br><p>Que esperas, no te lo pierdas!!  </p>", client.getEmail(), "No te pierdas esta oferta de RENTUY");
				}
			}
		}
	}
}
