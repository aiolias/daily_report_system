package services;

import javax.persistence.EntityManager;

import utils.DBUtil;

/**
 * DB接続に関わる共通処理を行うクラス
 */
public class ServiceBase {

    /**
     * EntityMnanagerインスタンス
     */
    protected EntityManager em = DBUtil.createEntitymanager();

    /**
     * EntityManagerのクローズ
     */
    public void close() {
        if (em.isOpen()) {
            em.close();
        }
    }

}
