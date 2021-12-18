package com.webank.webase.chain.mgr.repository.mapper;

import com.webank.webase.chain.mgr.repository.bean.TbUser;
import com.webank.webase.chain.mgr.repository.bean.TbUserExample;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TbUserMapper {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @SelectProvider(type = TbUserSqlProvider.class, method = "countByExample")
    long countByExample(TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @DeleteProvider(type = TbUserSqlProvider.class, method = "deleteByExample")
    int deleteByExample(TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @Delete({ "delete from tb_user", "where id = #{id,jdbcType=INTEGER}" })
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @Insert({ "insert into tb_user (sign_user_id, user_name, ", "address, chain_id, ", "group_id, user_status, ", "gmt_create, gmt_modified, ", "description)", "values (#{signUserId,jdbcType=VARCHAR}, #{userName,jdbcType=VARCHAR}, ", "#{address,jdbcType=VARCHAR}, #{chainId,jdbcType=INTEGER}, ", "#{groupId,jdbcType=INTEGER}, #{userStatus,jdbcType=INTEGER}, ", "#{gmtCreate,jdbcType=TIMESTAMP}, #{gmtModified,jdbcType=TIMESTAMP}, ", "#{description,jdbcType=VARCHAR})" })
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insert(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @InsertProvider(type = TbUserSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = Integer.class)
    int insertSelective(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @SelectProvider(type = TbUserSqlProvider.class, method = "selectByExample")
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "sign_user_id", property = "signUserId", jdbcType = JdbcType.VARCHAR), @Result(column = "user_name", property = "userName", jdbcType = JdbcType.VARCHAR), @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR), @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER), @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER), @Result(column = "user_status", property = "userStatus", jdbcType = JdbcType.INTEGER), @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP), @Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP), @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR) })
    List<TbUser> selectByExample(TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @Select({ "select", "id, sign_user_id, user_name, address, chain_id, group_id, user_status, gmt_create, ", "gmt_modified, description", "from tb_user", "where id = #{id,jdbcType=INTEGER}" })
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "sign_user_id", property = "signUserId", jdbcType = JdbcType.VARCHAR), @Result(column = "user_name", property = "userName", jdbcType = JdbcType.VARCHAR), @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR), @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER), @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER), @Result(column = "user_status", property = "userStatus", jdbcType = JdbcType.INTEGER), @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP), @Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP), @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR) })
    TbUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbUserSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TbUser record, @Param("example") TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbUserSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") TbUser record, @Param("example") TbUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbUserSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @Update({ "update tb_user", "set sign_user_id = #{signUserId,jdbcType=VARCHAR},", "user_name = #{userName,jdbcType=VARCHAR},", "address = #{address,jdbcType=VARCHAR},", "chain_id = #{chainId,jdbcType=INTEGER},", "group_id = #{groupId,jdbcType=INTEGER},", "user_status = #{userStatus,jdbcType=INTEGER},", "gmt_create = #{gmtCreate,jdbcType=TIMESTAMP},", "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},", "description = #{description,jdbcType=VARCHAR}", "where id = #{id,jdbcType=INTEGER}" })
    int updateByPrimaryKey(TbUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert({ "<script>", "insert into tb_user (sign_user_id, ", "user_name, address, ", "chain_id, group_id, ", "user_status, gmt_create, ", "gmt_modified, description)", "values<foreach collection=\"list\" item=\"detail\" index=\"index\" separator=\",\">(#{detail.signUserId,jdbcType=VARCHAR}, ", "#{detail.userName,jdbcType=VARCHAR}, #{detail.address,jdbcType=VARCHAR}, ", "#{detail.chainId,jdbcType=INTEGER}, #{detail.groupId,jdbcType=INTEGER}, ", "#{detail.userStatus,jdbcType=INTEGER}, #{detail.gmtCreate,jdbcType=TIMESTAMP}, ", "#{detail.gmtModified,jdbcType=TIMESTAMP}, #{detail.description,jdbcType=VARCHAR})</foreach></script>" })
    int batchInsert(List<TbUser> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_user
     *
     * @mbg.generated
     */
    @SelectProvider(type = TbUserSqlProvider.class, method = "getOneByExample")
    @Results({ @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true), @Result(column = "sign_user_id", property = "signUserId", jdbcType = JdbcType.VARCHAR), @Result(column = "user_name", property = "userName", jdbcType = JdbcType.VARCHAR), @Result(column = "address", property = "address", jdbcType = JdbcType.VARCHAR), @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER), @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER), @Result(column = "user_status", property = "userStatus", jdbcType = JdbcType.INTEGER), @Result(column = "gmt_create", property = "gmtCreate", jdbcType = JdbcType.TIMESTAMP), @Result(column = "gmt_modified", property = "gmtModified", jdbcType = JdbcType.TIMESTAMP), @Result(column = "description", property = "description", jdbcType = JdbcType.VARCHAR) })
    Optional<TbUser> getOneByExample(TbUserExample example);
}