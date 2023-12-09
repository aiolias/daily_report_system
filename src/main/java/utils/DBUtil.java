package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import constants.JpaConst;

public class DBUtil {

    private static EntityManagerFactory emf;

    //EntityMnaagerインスタンス生成
    public static EntityManager createEntitymanager() {
        return _getEntityManagerFactory().createEntityManager();
    }

    //EntitymanagerFactoryインスタンスを生成
    private static EntityManagerFactory _getEntityManagerFactory() {
        if(emf == null) {
           emf = Persistence.createEntityManagerFactory(JpaConst.PERSISTENCE_UNIT_NAME);
        }

        return emf;
    }
}
