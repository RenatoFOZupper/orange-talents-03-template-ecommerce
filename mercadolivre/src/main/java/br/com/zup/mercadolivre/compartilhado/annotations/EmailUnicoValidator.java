package br.com.zup.mercadolivre.compartilhado.annotations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;


public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, Object> {

	private String domainAttribute;
	private Class<?> klass;

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialize(EmailUnico params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = em
				.createQuery("SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + " =:VALUE");
		query.setParameter("VALUE", value);

		List<?> list = query.getResultList();
		Assert.state(list.size() <= 1,
				"Foi encontrado mais de um campo com o atributo " + domainAttribute + " = " + value);

		return list.isEmpty();
	}

}
