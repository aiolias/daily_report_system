package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Like;

/**
 * いいねデータのDTOモデル⇔viewモデルの変換を行うクラス
 *
 */
public class LikeConverter {
    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param lv GoodViewのインスタンス
     * @return Likeのインスタンス
     */
    public static Like toModel(LikeView lv) {
        return new Like(
                lv.getId(),
                ReportConverter.toModel(lv.getReport()),
                EmployeeConverter.toModel(lv.getEmployee()),
                lv.getCreatedAt(),
                lv.getUpdatedAt());
    }

    /** DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param g Goodのインスタンス
     * @return GoodViewのインスタンス
     */
    public static LikeView toView(Like l) {
        if (l== null) {
            return null;
        }

        return new LikeView(
                l.getId(),
                ReportConverter.toView(l.getReport()),
                EmployeeConverter.toView(l.getEmployee()),
                l.getCreatedAt(),
                l.getUpdatedAt());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<LikeView> toViewList(List<Like> list) {
        List<LikeView> evs = new ArrayList<>();
        for (Like l : list) {
            evs.add(toView(l));
        }
        return evs;

    }

    /**
     * Viewモデルの全フィールドの内容えおDTOモデルのフィールドにコピーする
     * @param r DTOモデル（コピー先）
     * @param rv Viewモデル（コピー先）
     */
    public static void copyViewToModel(Like l, LikeView lv) {
        l.setId(lv.getId());
        l.setReport(ReportConverter.toModel(lv.getReport()));
        l.setEmployee(EmployeeConverter.toModel(lv.getEmployee()));
        l.setCreatedAt(lv.getCreatedAt());
        l.setUpdatedAt(lv.getUpdatedAt());
    }
}
