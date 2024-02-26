package it.unipv.ingsw.UniBook.Controller;

import it.unipv.ingsw.UniBook.Exception.DatabaseException;
import it.unipv.ingsw.UniBook.Exception.EmptyFieldException;
import it.unipv.ingsw.UniBook.Exception.PopupManager;
import it.unipv.ingsw.UniBook.Exception.ResourceAlreadyRentedException;
import it.unipv.ingsw.UniBook.Exception.WrongFieldException;
import it.unipv.ingsw.UniBook.Model.Payment;
import it.unipv.ingsw.UniBook.Model.Renting;
import it.unipv.ingsw.UniBook.View.ManagementRentingView;
import it.unipv.ingsw.UniBook.View.PaymentView;
import it.unipv.ingsw.UniBook.View.RentingView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RentingController {

	private Renting model;
	private RentingView view;
	private Payment pModel;
	private PaymentView pView;
	private ManagementRentingView mrView;

	public RentingController(Renting model, ManagementRentingView view) {
		this.mrView = view;
		this.model = model;
		createManagementView();
	}

	public RentingController(Renting model, RentingView view) {
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
				if (model.tryToRent()) {
					createPaymentView(model, true, 0);
					pView.setVisible(true);
				}
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

	private void createPaymentView(Renting model, boolean s, double p) {

		pView = new PaymentView(model.getPrice() - p);
		ActionListener confirmPayment = new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				manageAction();
			}

			private void manageAction() {
				pModel = new Payment(pView.getCardNumberField(), pView.getCvvField());
				if (s) {
					try {
						if (pModel.check())
							if (model.tryToInsert()) {
								pView.dispose();
								view.dispose();
							} else
								pView.dispose();
					} catch (WrongFieldException e) {
						e.showPopup();
					} catch (DatabaseException e) {
						e.showPopup();
					}
				} else {
					try {
						if (pModel.check())
							if (model.tryToModify()) {
								pView.dispose();
								mrView.dispose();
							} else
								pView.dispose();
					} catch (DatabaseException e) {
						e.showPopup();
					}
					catch(WrongFieldException e) {
						e.showPopup();
					}
				}
			}
		};
		pView.getPayButton().addActionListener(confirmPayment);
	}

	private void createManagementView() {
		mrView.updateTable(model.getUserRenting());
		mrView.setVisible(true);

		ActionListener extendRent = new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				manageAction();
			}

			private void manageAction() {
				try {
					LocalDate today = LocalDate.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					Renting r = mrView.getSelectedRow();
					LocalDate date = LocalDate.parse(r.getEndDate(), formatter);
					if (date.compareTo(today) >= 0) {
						PopupManager.dateChoosing(mrView.getNewEndDateChooser(), date);
						if (mrView.getNewEndDate() == "")
							throw new EmptyFieldException();
						r.setEndDate(mrView.getNewEndDate());
						double p = r.getPrice();
						r.setTotalPrice();
						if (r.check(r, 1)) {
							createPaymentView(r, false, p);
							pView.setVisible(true);
						}
					} else {
						new WrongFieldException().showPopup("Il periodo di affitto è già scaduto");
					}
				} catch (DatabaseException e) {
					e.showPopup();
				} catch (EmptyFieldException e) {
					e.showPopup();
				} catch (ResourceAlreadyRentedException e) {
					e.showPopup();
				}

			}
		};
		mrView.getExtendButton().addActionListener(extendRent);
	}

}