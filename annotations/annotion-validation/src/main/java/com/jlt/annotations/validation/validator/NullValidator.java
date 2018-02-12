package com.jlt.annotations.validation.validator;

import java.util.Objects;

/**
 * An implementation of Validator for performing null check.
 * 
 * @author Prabal ghura
 *
 */
public class NullValidator extends Validator{

	public NullValidator() {
		super();
	}

	@Override
	protected void validate() {
		if(Objects.isNull(content))
			addError("Field should not be null");
	}
}
