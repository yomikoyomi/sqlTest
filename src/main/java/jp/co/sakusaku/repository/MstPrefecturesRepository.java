package jp.co.sakusaku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sakusaku.entity.MstPrefectures;

@Repository
public interface MstPrefecturesRepository extends JpaRepository<MstPrefectures, Long> {

}
