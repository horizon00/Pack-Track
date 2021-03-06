package model.managers;

import java.util.List;

import model.db.HibernateUtil;
import model.models.Device;
import model.models.Dist;
import model.models.Station;
import model.models.User;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

public class StationManager {

	@Deprecated
	public boolean addStation(Station station) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(station);
			session.getTransaction().commit();
			session.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Deprecated
	public boolean removeStation(Station station) {

		try {

			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(station);
			session.getTransaction().commit();
			session.close();
			HibernateUtil.getSessionFactory().close();
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * Gets a list of Stations
	 * 
	 * @return list of Stations
	 */
	public List<Station> getStationList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Station");
		List<Station> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	/**
	 * Gets a station by ID
	 * @param id Station ID
	 * @return Station Object
	 */
	public Station getStation(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Station where id=:id");
		query.setInteger("id", id);
		List<Station> list = query.list();
		session.getTransaction().commit();
		session.close();

		if (list.size() == 0) {
			System.out.println("null");
			return null;
		}

		return list.get(0);
	}

	@Deprecated
	public boolean deleteStation(int id, String by) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			SQLQuery query = session
					.createSQLQuery("update Station set deleted=1, deleteby=:by where stationid=:id");
			query.setInteger("id", id);
			query.setString("by", by);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Deprecated
	public boolean updateStation(Station station) {

		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(station);
			session.getTransaction().commit();
			session.close();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Gets station distances from distance table
	 * @return list of distances 
	 */
	public List<Dist> getDistList() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Dist");
		List<Dist> list = query.list();
		session.getTransaction().commit();
		session.close();
		return list;
	}
}
