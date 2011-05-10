package br.com.caelum.stella.hibernate.validator.xml.logic;

import java.util.Arrays;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.hibernate.validator.Validator;

import br.com.caelum.stella.hibernate.validator.xml.Min;

final public class StellaMinValidator implements
		ConstraintValidator<Min, Object> {

	private Min annotation;

	@SuppressWarnings("unchecked")
	private final List<Class<?>> acceptedTypes = (List) Arrays.asList(
			Byte.class, Short.class, Integer.class, Long.class);

	public void initialize(final Min annotation) {
		this.annotation = annotation;

	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}

		if (!acceptedTypes.contains(value.getClass())) {
			throw new IllegalStateException(Min.class.getSimpleName()
					+ " cannot be used to annotate a field of type "
					+ value.getClass().getName()
					+ ". Only Byte, Short, Integer and Long are accepted.");
		}

		Long val = new Long(value.toString());

		if (val < annotation.value()) {
			return false;
		}

		return true;
	}
}