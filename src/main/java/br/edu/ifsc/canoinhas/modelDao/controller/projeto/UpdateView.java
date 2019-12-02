package br.edu.ifsc.canoinhas.modelDao.controller.projeto;

import br.edu.ifsc.canoinhas.controllerview.ControllerIDEView;

public class UpdateView {
	private ControllerIDEView controllerIDEView;
	private static UpdateView update;

	private UpdateView() {
	}

	public static UpdateView getInstance() {
		if (update == null) {
			update = new UpdateView();
		}
		return update;
	}

	public UpdateView(ControllerIDEView controllerIDEView) {
		this.controllerIDEView = controllerIDEView;
	}

	public ControllerIDEView getControllerIDEView() {
		return controllerIDEView;
	}

	public void setControllerIDEView(ControllerIDEView controllerIDEView) {
		this.controllerIDEView = controllerIDEView;
	}

}
