package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.View.RentingView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RentingController {
	
	private Renting model;
	private RentingView view;
	
	public RentingController() {}
	
	public RentingController(Renting model,RentingView view) {
		this.model = model;
		this.view = view;
		initComponent();
	}
	
	private void initComponent() {
		ActionListener confirm = new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				manageAction();
			}
			private void manageAction() {
				model.setStartDate(view.getStartDate());
				model.setEndDate(view.getEndDate());
				model.setResource(view.getSelectedResource());
				/*
				 * TODO methods for proceeding
				model.check();
				model.proceedPayment();*/
			}
		};
		view.getConfirmButton().addActionListener(confirm);
	}
}
