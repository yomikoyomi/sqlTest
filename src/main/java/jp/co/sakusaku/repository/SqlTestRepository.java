package jp.co.sakusaku.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.sakusaku.entity.SqlTest;

@Repository
public interface SqlTestRepository extends JpaRepository<SqlTest, Long> {

}
