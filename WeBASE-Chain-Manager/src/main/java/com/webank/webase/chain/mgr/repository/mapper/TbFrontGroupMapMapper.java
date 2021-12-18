package com.webank.webase.chain.mgr.repository.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import com.webank.webase.chain.mgr.frontgroupmap.entity.FrontGroup;
import com.webank.webase.chain.mgr.frontgroupmap.entity.MapListParam;
import com.webank.webase.chain.mgr.repository.bean.TbFrontGroupMap;
import com.webank.webase.chain.mgr.repository.bean.TbFrontGroupMapExample;
import java.util.Optional;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Update;

public interface TbFrontGroupMapMapper {

    @SelectProvider(type = TbFrontGroupMapSqlProvider.class, method = "countByParam")
    int countByParam(MapListParam param);

    @Select({ "select a.map_id as map_id, a.chain_id as chain_id, a.group_id as group_id, a.front_id as front_id, " + "b.front_ip as front_ip,b.front_port as front_port,b.front_peer_name as front_peer_name from tb_front_group_map a left join tb_front b " + "on(a.front_id=b.front_id) left join tb_group c " + "on(a.chain_id=c.chain_id and a.group_id=c.group_id) left join tb_chain d " + "on(a.chain_id=d.chain_id) where a.group_id = #{groupId} " })
    List<FrontGroup> selectByGroupId(@Param("groupId") int groupId);

    @Select({ "select a.map_id as map_id, a.chain_id as chain_id, a.group_id as group_id, a.front_id as front_id," + "b.front_ip as front_ip,b.front_port as front_port,b.front_peer_name as front_peer_name from tb_front_group_map a left join tb_front b " + "on(a.front_id=b.front_id) left join tb_group c " + "on(a.chain_id=c.chain_id and a.group_id=c.group_id) left join tb_chain d " + "on(a.chain_id=d.chain_id) where a.group_id = #{groupId}  and a.chain_id = #{chainId} " })
    List<FrontGroup> selectByChainIdAndGroupId(@Param("chainId") int chainId, @Param("groupId") int groupId);

    @Select({ "select a.map_id as map_id, a.chain_id as chain_id, a.group_id as group_id, a.front_id as front_id," + "b.front_ip as front_ip,b.front_port as front_port,b.front_peer_name as front_peer_name from tb_front_group_map a left join tb_front b " + "on(a.front_id=b.front_id) left join tb_group c " + "on(a.chain_id=c.chain_id and a.group_id=c.group_id) left join tb_chain d " + "on(a.chain_id=d.chain_id) where a.chain_id = #{chainId} " })
    List<FrontGroup> selectByChainId(@Param("chainId") int chainId);

    @Delete({ "delete from tb_front_group_map where chain_id = #{chainId}" })
    int deleteByChainId(@Param("chainId") int chainId);

    @Delete({ "delete from tb_front_group_map where front_id = #{frontId}" })
    int deleteByFrontId(@Param("frontId") int frontId);

    @Delete({ "delete from tb_front_group_map  where chain_id = #{chainId} and group_id = #{groupId}" })
    int deleteByGroupId(@Param("chainId") int chainId, @Param("groupId") int groupId);

    @Delete({ "delete from tb_front_group_map where chain_id = #{chainId} and front_id = #{frontId} and group_id = #{groupId}" })
    int deleteByChainIdAndFrontIdAndGroupId(@Param("chainId") int chainId, @Param("frontId") int frontId, @Param("groupId") int groupId);

    @Select({ "select * from tb_front_group_map where chain_id = #{chainId} and front_id = #{frontId} and group_id = #{groupId}" })
    TbFrontGroupMap selectByChainIdAndFrontIdAndGroupId(@Param("chainId") int chainId, @Param("frontId") int frontId, @Param("groupId") int groupId);

    @Select({ "select * from tb_front_group_map where chain_id=#{chainId} and group_id=#{groupId} order by create_time desc" })
    List<TbFrontGroupMap> selectListByGroupId(@Param("chainId") int chainId, @Param("groupId") int groupId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @SelectProvider(type = TbFrontGroupMapSqlProvider.class, method = "countByExample")
    long countByExample(TbFrontGroupMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @DeleteProvider(type = TbFrontGroupMapSqlProvider.class, method = "deleteByExample")
    int deleteByExample(TbFrontGroupMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @Delete({ "delete from tb_front_group_map", "where map_id = #{mapId,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer mapId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @Insert({ "insert into tb_front_group_map (chain_id, front_id, ", "group_id, create_time, ", "modify_time, front_status)", "values (#{chainId,jdbcType=INTEGER}, #{frontId,jdbcType=INTEGER}, ", "#{groupId,jdbcType=INTEGER}, #{createTime,jdbcType=TIMESTAMP}, ", "#{modifyTime,jdbcType=TIMESTAMP}, #{frontStatus,jdbcType=TINYINT})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "mapId", before = false, resultType = Integer.class)
    int insert(TbFrontGroupMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @InsertProvider(type = TbFrontGroupMapSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "mapId", before = false, resultType = Integer.class)
    int insertSelective(TbFrontGroupMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @SelectProvider(type = TbFrontGroupMapSqlProvider.class, method = "selectByExample")
    @Results({ @Result(column = "map_id", property = "mapId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER), @Result(column = "front_id", property = "frontId", jdbcType = JdbcType.INTEGER), @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER), @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "front_status", property = "frontStatus", jdbcType = JdbcType.TINYINT) })
    List<TbFrontGroupMap> selectByExample(TbFrontGroupMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @Select({ "select", "map_id, chain_id, front_id, group_id, create_time, modify_time, front_status", "from tb_front_group_map", "where map_id = #{mapId,jdbcType=INTEGER}" })
    @Results({ @Result(column = "map_id", property = "mapId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER), @Result(column = "front_id", property = "frontId", jdbcType = JdbcType.INTEGER), @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER), @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "front_status", property = "frontStatus", jdbcType = JdbcType.TINYINT) })
    TbFrontGroupMap selectByPrimaryKey(Integer mapId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbFrontGroupMapSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TbFrontGroupMap record, @Param("example") TbFrontGroupMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbFrontGroupMapSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") TbFrontGroupMap record, @Param("example") TbFrontGroupMapExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbFrontGroupMapSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TbFrontGroupMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @Update({ "update tb_front_group_map", "set chain_id = #{chainId,jdbcType=INTEGER},", "front_id = #{frontId,jdbcType=INTEGER},", "group_id = #{groupId,jdbcType=INTEGER},", "create_time = #{createTime,jdbcType=TIMESTAMP},", "modify_time = #{modifyTime,jdbcType=TIMESTAMP},", "front_status = #{frontStatus,jdbcType=TINYINT}", "where map_id = #{mapId,jdbcType=INTEGER}" })
    int updateByPrimaryKey(TbFrontGroupMap record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @Options(useGeneratedKeys = true, keyProperty = "mapId", keyColumn = "map_id")
    @Insert({ "<script>", "insert into tb_front_group_map (chain_id, ", "front_id, group_id, ", "create_time, modify_time, ", "front_status)", "values<foreach collection=\"list\" item=\"detail\" index=\"index\" separator=\",\">(#{detail.chainId,jdbcType=INTEGER}, ", "#{detail.frontId,jdbcType=INTEGER}, #{detail.groupId,jdbcType=INTEGER}, ", "#{detail.createTime,jdbcType=TIMESTAMP}, #{detail.modifyTime,jdbcType=TIMESTAMP}, ", "#{detail.frontStatus,jdbcType=TINYINT})</foreach></script>" })
    int batchInsert(List<TbFrontGroupMap> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_front_group_map
     *
     * @mbg.generated
     */
    @SelectProvider(type = TbFrontGroupMapSqlProvider.class, method = "getOneByExample")
    @Results({ @Result(column = "map_id", property = "mapId", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER), @Result(column = "front_id", property = "frontId", jdbcType = JdbcType.INTEGER), @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER), @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP), @Result(column = "front_status", property = "frontStatus", jdbcType = JdbcType.TINYINT) })
    Optional<TbFrontGroupMap> getOneByExample(TbFrontGroupMapExample example);
}
