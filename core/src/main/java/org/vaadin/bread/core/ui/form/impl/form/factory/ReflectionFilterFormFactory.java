package org.vaadin.bread.core.ui.form.impl.form.factory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import org.vaadin.bread.core.ui.filter.FilterComponent;
import org.vaadin.bread.core.ui.form.FilterOperation;
import org.vaadin.bread.core.ui.form.FormConfiguration;
import org.vaadin.bread.core.ui.form.OperationAction;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Sizeable.Unit;
import com.vaadin.shared.util.SharedUtil;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class ReflectionFilterFormFactory implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private FormConfiguration conf = new FormConfiguration();
	
	public ReflectionFilterFormFactory() {
		conf.getOperationActions().add(FilterOperation.APPLY);
		conf.getOperationActions().add(FilterOperation.EMPTY);
		
		conf.getOperationActions().stream().forEach(op->
			conf.setButtonCaption(op, SharedUtil.propertyIdToHumanFriendly(op.getActionName()))
		);
	}

    public Component buildNewForm(Class<?> clazz) {
        FilterComponent filterComponent = new FilterComponent(clazz);
        filterComponent.setSizeFull();
        filterComponent.setSpacing(true);

        Layout footerLayout = buildFooter();

        VerticalLayout mainLayout = new VerticalLayout(filterComponent, footerLayout);
        mainLayout.setComponentAlignment(footerLayout, Alignment.BOTTOM_RIGHT);
        mainLayout.setMargin(true);
        mainLayout.setSpacing(true);
        mainLayout.setWidth(100, Unit.PERCENTAGE);

        return mainLayout;
    }
    
    protected Layout buildFooter() {
    	ArrayList<Button> buttons = new ArrayList<>();
    	for (OperationAction operationAction : conf.getOperationActions()) {
			
    		Button btn = buildOperationButton(operationAction, conf.getOperationActionListener(operationAction));
    		if (btn!=null) {
    			buttons.add(btn);
    		}
		}
    	
    	
    	
        HorizontalLayout footerLayout = new HorizontalLayout();
        footerLayout.setSizeUndefined();
        footerLayout.setSpacing(true);
        if (!buttons.isEmpty()) {
            footerLayout.addComponents(buttons.toArray(new Button[] {}));
        }

        return footerLayout;
    }


    protected Button buildOperationButton(OperationAction operationAction, Button.ClickListener clickListener) {
        if (clickListener == null || operationAction==null) {
            return null;
        }

        Button button = new Button(conf.getButtonCaption(operationAction)
        		, conf.getButtonIcon(operationAction)
        		);
        Set<String> set = conf.getButtonStyleNames(operationAction);
        if (set!=null) {
        	set.forEach(styleName -> {
        		button.addStyleName(styleName);
        		if (styleName.equals(ValoTheme.BUTTON_PRIMARY))
        			button.setClickShortcut(KeyCode.ENTER);
        	});
        	
        }
        button.addClickListener(event -> {
        	clickListener.buttonClick(event);
        });
        return button;
    }

	public FormConfiguration getConf() {
		return conf;
	}

	public void setConf(FormConfiguration conf) {
		this.conf = conf;
	}

}
