package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.LikeConverter;
import actions.views.LikeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.JpaConst;
import models.Like;

/**
 * いいねテーブルの操作に関わる処理を行うクラス
 */
public class LikeService extends ServiceBase {

    /**
     * 指定した日報のいいねされたデータを、指定されたページ数の一覧画面に表示する分取得しLikeViewのリストで返却する
     * @param report 日報
     * @param page ページ数
     * @return 一覧画面に表示するデータのリスト
     */
    public List<LikeView> getMinePerPage(ReportView report, int page) {
      List<Like> likes = em.createNamedQuery(JpaConst.Q_LIKE_GET_ALL_MINE,Like.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT,ReportConverter.toModel(report))
                .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(JpaConst.ROW_PER_PAGE)
                .getResultList();
        return LikeConverter.toViewList(likes);
    }
    /**
     * 指定した日報のいいねされたデータの件数を取得し、返却する
     * @param report
     * return いいねされたデータの件数
     */
    public long countAllMine(ReportView report) {

        long count = (long) em.createNamedQuery(JpaConst.Q_LIKE_COUNT_ALL_MINE,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT, ReportConverter.toModel(report))
                .getSingleResult();
        return count;
    }
    /**
     * いいねされた時にデータを１件作成して、いいねテーブルに登録する
     * @param lv いいねの登録内容
     */
    public void create(LikeView lv) {
        LocalDateTime ldt = LocalDateTime.now();
        lv.setCreatedAt(ldt);
        lv.setUpdatedAt(ldt);
        createInternal(lv);
    }
    /**
     * いいねデータを１件登録する
     * @param lv いいねデータ
     */
    private void createInternal(LikeView lv) {
        em.getTransaction().begin();
        em.persist(LikeConverter.toModel(lv));
        em.getTransaction().commit();
    }
    /**
     * 指定した日報のいいねされたデータを全件取得しLikeViewリストで返却する
     * @param report 日報
     * return 一覧画面に表示するいいねデータのリスト
     */
    public List<LikeView> getAllMine(ReportView report) {
        List<Like> likes = em.createNamedQuery(JpaConst.Q_LIKE_GET_ALL_MINE,Like.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT,ReportConverter.toModel(report))
                .getResultList();
        return LikeConverter.toViewList(likes);
    }
    /**
     * 指定した日報にログイン中の従業員がいいねした件数を取得し返却する
     * @param rv,ev
     * @return 指定した日報にログイン中の従業員がいいねした件数
     */
    public long getCountLiked(ReportView rv,EmployeeView ev) {
        long count = (long)em.createNamedQuery(JpaConst.Q_LIKE_COUNT_LIKED,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REPORT,ReportConverter.toModel(rv))
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE,EmployeeConverter.toModel(ev))
                .getSingleResult();
        return count;
    }
}
