package org.vaadin.crudui.form.impl.form.factory;

import java.util.List;

import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.form.AbstractAutoGeneratedCrudFormFactory;

import com.vaadin.data.HasValue;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author Alejandro Duarte
 */
public class VerticalCrudFormFactory<T> extends AbstractAutoGeneratedCrudFormFactory<T> {

    public VerticalCrudFormFactory(Class<T> domainType) {
        super(domainType);
    }

    @Override
    public Component buildNewForm(CrudOperation operation, T domainObject, boolean readOnly, Button.ClickListener cancelButtonClickListener, Button.ClickListener operationButtonClickListener) {
        FormLayout formLayout = new FormLayout();

        List<HasValue> fields = buildFields(operation, domainObject, readOnly);
        fields.stream().forEach(field -> formLayout.addComponent((Component) field));

        Layout footerLayout = buildFooter(operation, domainObject, cancelButtonClickListener, operationButtonClickListener);

        VerticalLayout mainLayout = new VerticalLayout(formLayout, footerLayout);
        mainLayout.setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT);
        mainLayout.setMargin(true);

        return mainLayout;
    }

}
