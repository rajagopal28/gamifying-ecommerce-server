package com.dxtrs.hack.gamify.repository;

import com.dxtrs.hack.gamify.dto.ChartData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChartDataRepository extends CrudRepository<ChartData, Long> {
    @Query(value = "SELECT FLOOR(10000 + RAND() * 89999)  as id, COUNT(o.id) as value, concat(concat(u.first_name, ' '), u.last_name) as label FROM orders o INNER JOIN users u on o.user_id=u.id group by o.user_id", nativeQuery = true)
    List<ChartData> countOfOrdersByUsers();

    @Query(value = "SELECT FLOOR(10000 + RAND() * 89999)  as id, COUNT(o.id) as value, p.category as label FROM orders o INNER JOIN products p on o.product_id=p.id group by p.category", nativeQuery = true)
    List<ChartData> countOfOrdersByCategory();

    @Query(value = "select  m.level_name as label, count(m.level_name) as value, FLOOR(10000 + RAND() * 89999) as id from board_mapper m," +
            "(select o.user_id as user_id , count(o.id) as orders_count from orders o INNER JOIN products p on o.product_id = p.id where p.category=:categoryValue group by o.user_id, p.category) as ord " +
            " where ord.orders_count <= m.upper_limit and ord.orders_count >= m.lower_limit and m.board_type='category' group by m.level_name", nativeQuery = true)
    List<ChartData> countOfCategoryBoard(@Param("categoryValue") String categoryName);


    @Query(value = "select  m.level_name as label, count(m.level_name) as value, FLOOR(10000 + RAND() * 89999) as id from board_mapper m," +
            "(select o.user_id as user_id , count(o.id) as orders_count from orders o INNER JOIN products p on o.product_id = p.id where p.sub_category=:subCategoryValue group by o.user_id, p.sub_category) as ord " +
            " where ord.orders_count <= m.upper_limit and ord.orders_count >= m.lower_limit and m.board_type='sub_category' group by m.level_name", nativeQuery = true)
    List<ChartData> countOfSubCategoryBoard(@Param("subCategoryValue") String subCategoryName);
}
