package br.edu.ifsc.canoinhas.utility;

import br.edu.ifsc.canoinhas.controllerview.ControllerClassView;
import br.edu.ifsc.canoinhas.controllerview.ControllerIDEView;
import br.edu.ifsc.canoinhas.controllerview.ControllerLoginView;

public class ControllerReferenceIDE {

	private static ControllerReferenceIDE controllerReferenceIDE;
	private ControllerIDEView controllerIDEView;
	private ControllerClassView controllerClassView;
	private ControllerLoginView controllerLogin;

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

	public ControllerClassView getControllerClassView() {
		return controllerClassView;
	}

	public void setControllerClassView(ControllerClassView controllerClassView) {
		this.controllerClassView = controllerClassView;
	}

	public ControllerLoginView getControllerLogin() {
		return controllerLogin;
	}

	public void setControllerLogin(ControllerLoginView controllerLogin) {
		this.controllerLogin = controllerLogin;
	}

}
