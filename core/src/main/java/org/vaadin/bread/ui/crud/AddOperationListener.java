package org.vaadin.bread.ui.crud;

import java.io.Serializable;

/**
 * @author Alejandro Duarte.
 */
@FunctionalInterface
public interface AddOperationListener<T> extends Serializable {

    T perform(T domainObject);

}