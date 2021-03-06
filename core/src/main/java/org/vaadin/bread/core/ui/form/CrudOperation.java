package org.vaadin.bread.core.ui.form;

/**
 * @author Alejandro Duarte.
 */
public enum CrudOperation implements OperationMode, OperationAction {

    CANCEL, READ, ADD, UPDATE, DELETE;

	/* (non-Javadoc)
	 * @see org.vaadin.bread.ui.crud.OperationAction#getActionName()
	 */
	@Override
	public String getActionName() {
		return name();
	}

	/* (non-Javadoc)
	 * @see org.vaadin.bread.ui.crud.OperationMode#getOperationModeName()
	 */
	@Override
	public String getOperationModeName() {
		return name();
	}

}
