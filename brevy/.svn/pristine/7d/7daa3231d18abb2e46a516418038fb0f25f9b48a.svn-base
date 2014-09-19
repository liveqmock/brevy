package com.brevy.fw.common.support.data.jpa;

import java.sql.Connection;
import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;

/**
 * 支持隔离级别修改
 * 
 * @author George
 * 
 */
public class CustomHibernateJpaDialect extends HibernateJpaDialect {

	private static final long serialVersionUID = -5742231714984068309L;	

	@Override
	public Object beginTransaction(EntityManager entityManager,
			final TransactionDefinition definition)
			throws PersistenceException, SQLException, TransactionException {

		if (definition.getTimeout() != TransactionDefinition.TIMEOUT_DEFAULT) {
			getSession(entityManager).getTransaction().setTimeout(definition.getTimeout());
		}
		if (definition.getIsolationLevel() != TransactionDefinition.ISOLATION_DEFAULT) {
			log.info("Starting to change isolation level to: {}", new Object[]{definition.getIsolationLevel()});
			Session session = (Session) entityManager.getDelegate();
			session.doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					DataSourceUtils.prepareConnectionForTransaction(connection, definition);
				}
			});
		}		
		entityManager.getTransaction().begin();
		return prepareTransaction(entityManager, definition.isReadOnly(), definition.getName());
	}
	
	private transient Logger log = LoggerFactory.getLogger(this.getClass());
}
