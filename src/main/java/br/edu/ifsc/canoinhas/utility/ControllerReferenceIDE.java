package br.edu.ifsc.canoinhas.utility;

import br.edu.ifsc.canoinhas.controllerview.ControllerIDEView;

public class ControllerReferenceIDE {

	private static ControllerReferenceIDE controllerReferenceIDE;
	private ControllerIDEView controllerIDEView;

	public static ControllerReferenceIDE getInstace() {
		if (controllerReferenceIDE == null) {
			controllerReferenceIDE = new ControllerReferenceIDE();
		}
		return controllerReferenceIDE;
	}

	public ControllerIDEView getControllerIDEView() {
		return controllerIDEView;
	}

	public void setControllerIDEView(ControllerIDEView controllerIDEView) {
		this.controllerIDEView = controllerIDEView;
	}

}
