package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Exception.DatabaseException;
import it.unipv.ingsw.UniBook.Exception.WrongFieldException;
import it.unipv.ingsw.UniBook.Model.Payment;
import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.View.PaymentView;
import it.unipv.ingsw.UniBook.View.RentingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RentingController {
	
	private Renting model;
	private RentingView view;
	private Payment pModel;
	private PaymentView pView;
	
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
				model.setTotalPrice();
				if(model.tryToRent()) {
				createPaymentView(model);
				pView.setVisible(true);}
			}
		};
		view.getConfirmButton().addActionListener(confirm);
		
		ActionListener cancel = new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				manageAction();
			}
			private void manageAction() {
				view.dispose();
			}
		};
		view.getCancelButton().addActionListener(cancel);
		
	}
	
	private void createPaymentView(Renting model) {
		
		pView = new PaymentView(model.getPrice());
		ActionListener confirmPayment = new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				manageAction();
			}
			private void manageAction() {
				pModel = new Payment(pView.getCardNumberField(), pView.getCvvField());
				try {
					if(pModel.check())
						if(model.tryToInsert()) {
							pView.dispose();
							view.dispose();
						}
						else
							pView.dispose();
				}catch(WrongFieldException e) {
					e.showPopup();
				}catch(DatabaseException e) {
					e.showPopup();
				}
			}
		};
		pView.getPayButton().addActionListener(confirmPayment);
	}
	
}