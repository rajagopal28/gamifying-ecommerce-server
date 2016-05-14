package com.dxtrs.hack.gamify.repository;

import com.dxtrs.hack.gamify.model.BoardEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends CrudRepository<BoardEntry, Long> {


    @Query(value = "select  ord.user_id as user_id, m.level_name as level_name, ord.orders_count as level_value, ord.segment_value as board_segment_value, FLOOR(10000 + RAND() * 89999) as id, m.board_type as board_segment_type  from board_mapper m," +
            " (select o.user_id as user_id , count(o.id) as orders_count, p.category as segment_value from orders o INNER JOIN products p on o.product_id = p.id where p.category=:categoryValue group by o.user_id, p.category) as ord" +
            " where ord.orders_count <= m.upper_limit and ord.orders_count >= m.lower_limit and m.board_type='category'", nativeQuery = true)
    List<BoardEntry> getCategoryBoardEntriesOf(@Param("categoryValue") String categoryValue);

    @Query(value = "select ord.user_id as user_id, m.level_name as level_name, ord.orders_count as level_value, ord.segment_value as board_segment_value, FLOOR(10000 + RAND() * 89999) as id, m.board_type as board_segment_type from board_mapper m," +
            " (select o.user_id as user_id , count(o.id) as orders_count, p.sub_category as segment_value from orders o INNER JOIN products p on o.product_id = p.id where p.sub_category=:subCategoryValue group by o.user_id, p.sub_category) as ord" +
            " where ord.orders_count <= m.upper_limit and ord.orders_count >= m.lower_limit and m.board_type='sub_category'", nativeQuery = true)
    List<BoardEntry> getSubCategoryBoardEntriesOf(@Param("subCategoryValue") String subCategoryValue);

    @Query(value = "select ord.user_id as user_id, m.level_name as level_name, ord.orders_count as level_value, ord.segment_value as board_segment_value, FLOOR(10000 + RAND() * 89999) as id,m.board_type as board_segment_type from board_mapper m," +
            " (select o.user_id as user_id , count(o.id) as orders_count, p.category as segment_value from orders o INNER JOIN products p on o.product_id = p.id group by o.user_id, p.category) as ord" +
            " where ord.orders_count <= m.upper_limit and ord.orders_count >= m.lower_limit and m.board_type='category'", nativeQuery = true)
    List<BoardEntry> getAllCategoryBoardEntries();

    @Query(value = "select ord.user_id as user_id, m.level_name as level_name, ord.orders_count as level_value, ord.segment_value as board_segment_value, FLOOR(10000 + RAND() * 89999)  as id, m.board_type as board_segment_type from board_mapper m," +
            " (select o.user_id as user_id , count(o.id) as orders_count, p.sub_category as segment_value from orders o INNER JOIN products p on o.product_id = p.id group by o.user_id, p.sub_category) as ord" +
            " where ord.orders_count <= m.upper_limit and ord.orders_count >= m.lower_limit and m.board_type='sub_category'", nativeQuery = true)
    List<BoardEntry> getAllSubCategoryBoardEntries();
}
