package org.flycloud.web.exam3.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.flycloud.web.exam3.model.ExtractMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExamineModelServiceImpl implements ExamineModelService {

	@Inject
	private EntityManagerFactory entityManagerFactory;

	public List<ExtractMethod> queryExamineModelRowByBank(String bank) {
		EntityManager entityManager = null;
		Query query = null;
		List<ExtractMethod> rows = new ArrayList<ExtractMethod>();
		try {
			entityManager = entityManagerFactory.createEntityManager();
			query = entityManager
					.createNativeQuery("SELECT D.target_id AS targetId,"
							+ "S.name AS standardName,"
							+ "T.name AS targetName,"
							+ "SUM(D.score) AS sumScore " + "FROM deduction D "
							+ "LEFT JOIN standard S "
							+ "ON D.standard_id = S.id "
							+ "LEFT JOIN target T " + "ON D.target_id = T.id "
							+ "WHERE D.standard_id = ? "
							+ "AND D.isDeleted = 0 " + "GROUP BY D.target_id "
							+ "ORDER BY sumScore DESC");
			query.setParameter(1, bank);
			List list = query.getResultList();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					ExtractMethod item = new ExtractMethod();
					Object[] objs = (Object[]) list.get(i);
//					item.setTargetId(objs[0].toString());
//					item.setStandardName(objs[1].toString());
//					item.setTargetName(objs[2].toString());
//					item.setSumScore(objs[3].toString());
					rows.add(item);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entityManager != null) {
				entityManager.close();
			}
		}
		return rows;
	}
}
