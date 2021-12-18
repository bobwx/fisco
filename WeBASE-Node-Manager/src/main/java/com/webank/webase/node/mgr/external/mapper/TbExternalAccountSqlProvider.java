package com.webank.webase.node.mgr.external.mapper;

import com.webank.webase.node.mgr.base.enums.ExternalInfoType;
import com.webank.webase.node.mgr.external.entity.TbExternalAccount;
import com.webank.webase.node.mgr.user.entity.UserParam;
import org.apache.ibatis.jdbc.SQL;

public class TbExternalAccountSqlProvider {

    public String listJoin(UserParam param) {
        SQL sql = new SQL();
        String sqlStr = "ext.id extAccountId,ext.group_id groupId,ext.address address,ext.create_time createTime,ext.modify_time modifyTime, " +
            "b.userId,b.account,b.publicKey,b.signUserId,b.userType,b.userStatus,b.appId,b.description,b.hasPk,b.userName, " +
            "c.transCount,c.hashs " +
            "FROM tb_external_account ext " +
            "LEFT JOIN " +
            "( SELECT group_id,address,user_id userId,user_name userName,account account,public_key publicKey,sign_user_id signUserId, " +
            "user_type userType,user_status userStatus,app_id appId,description description,has_pk hasPk " +
            "FROM tb_user " +
            ") b on ext.address=b.address and ext.group_id=b.group_id " +
            "LEFT JOIN " +
            "( SELECT distinct(user_name),sum(trans_count) transCount, max(trans_hashs) hashs " +
            "FROM tb_user_transaction_monitor_${groupId} WHERE user_type=1 group by user_name" +
            // if external address equal to monitor user's username, it means user not imported
            ") c on ext.address=c.user_name";
        sql.SELECT(sqlStr);
        if (param.getGroupId() != null) {
            sql.WHERE("ext.group_id = #{groupId}");
        }
        if (param.getAccount() != null) {
            sql.WHERE("b.account = #{account}");
        }
        if (param.getCommParam() != null) {
            sql.WHERE("(" +
                    "b.userName like CONCAT(#{commParam},'%') " +
                    "or " +
                    "ext.address like CONCAT(#{commParam},'%')" +
                    ")");
        }
//        if (param.getUserName() != null) {
//            sql.WHERE("b.userName = #{userName}");
//        }
//        if (param.getAddress() != null) {
//            sql.WHERE("ext.address = #{address}");
//        }
        // get all or some
        // 1-all(default), 2-normal, 3-abnormal
        if (param.getType() == ExternalInfoType.NORMAL.getValue()) {
            sql.WHERE("b.userId is not NULL");
        } else if (param.getType() == ExternalInfoType.ABNORMAL.getValue()) {
            sql.WHERE("b.userId is NULL");
        }
        // page
        sql.ORDER_BY("ext.modify_time desc");
        if (param.getStart() != null && param.getPageSize() != null) {
            sql.LIMIT(param.getStart() + "," +param.getPageSize());
        }
        return sql.toString();
    }

    public String count(UserParam param) {
        SQL sql = new SQL();
        sql.SELECT("count(1) from tb_external_account ext "
            + "left join "
            + "(select user_id userId,group_id,address,account,user_name userName from tb_user) b "
            + "on ext.address=b.address and ext.group_id=b.group_id ");
        if (param.getGroupId() != null) {
            sql.WHERE("ext.group_id = #{groupId}");
        }
        if (param.getAccount() != null) {
            sql.WHERE("b.account = #{account}");
        }
        if (param.getCommParam() != null) {
            sql.WHERE("(" +
                    "b.userName like CONCAT(#{commParam},'%') " +
                    "or " +
                    "ext.address like CONCAT(#{commParam},'%')" +
                    ")");
        }
//        if (param.getUserName() != null) {
//            sql.WHERE("b.userName = #{userName}");
//        }
//        if (param.getAddress() != null) {
//            sql.WHERE("ext.address = #{address}");
//        }
        // get all or some
        // 1-all(default), 2-normal, 3-abnormal
        if (param.getType() == ExternalInfoType.NORMAL.getValue()) {
            sql.WHERE("b.userId is not NULL");
        } else if (param.getType() == ExternalInfoType.ABNORMAL.getValue()) {
            sql.WHERE("b.userId is NULL");
        }
        return sql.toString();
    }

    public String getList(UserParam param) {
        SQL sql = new SQL();
        sql.FROM("tb_external_account");
        sql.SELECT(ALL_COLUMN_FIELDS);
        if (param.getGroupId() != null) {
            sql.WHERE("group_id = #{groupId}");
        }
        if (param.getUserName() != null) {
            sql.WHERE("user_name = #{userName}");
        }
        if (param.getUserId() != null) {
            sql.WHERE("id = #{userId}");
        }
        // page
        sql.ORDER_BY("create_time ");
        if (param.getStart() != null && param.getPageSize() != null) {
            sql.LIMIT(param.getStart() + "," +param.getPageSize());
        }
        return sql.toString();
    }

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_external_account
     *
     * @mbg.generated
     */
    public static final String ALL_COLUMN_FIELDS = "id,group_id,public_key,address,sign_user_id,has_pk,user_name,user_status,create_time,modify_time,description,app_id";

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_external_account
     *
     * @mbg.generated
     */
    public String insertSelective(TbExternalAccount record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_external_account");
        if (record.getGroupId() != null) {
            sql.VALUES("group_id", "#{groupId,jdbcType=INTEGER}");
        }
        if (record.getPublicKey() != null) {
            sql.VALUES("public_key", "#{publicKey,jdbcType=VARCHAR}");
        }
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        if (record.getSignUserId() != null) {
            sql.VALUES("sign_user_id", "#{signUserId,jdbcType=VARCHAR}");
        }
        if (record.getHasPk() != null) {
            sql.VALUES("has_pk", "#{hasPk,jdbcType=INTEGER}");
        }
        if (record.getUserName() != null) {
            sql.VALUES("user_name", "#{userName,jdbcType=VARCHAR}");
        }
        if (record.getUserStatus() != null) {
            sql.VALUES("user_status", "#{userStatus,jdbcType=INTEGER}");
        }
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        if (record.getAppId() != null) {
            sql.VALUES("app_id", "#{appId,jdbcType=VARCHAR}");
        }
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_external_account
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TbExternalAccount record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_external_account");
        if (record.getGroupId() != null) {
            sql.SET("group_id = #{groupId,jdbcType=INTEGER}");
        }
        if (record.getPublicKey() != null) {
            sql.SET("public_key = #{publicKey,jdbcType=VARCHAR}");
        }
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        if (record.getSignUserId() != null) {
            sql.SET("sign_user_id = #{signUserId,jdbcType=VARCHAR}");
        }
        if (record.getHasPk() != null) {
            sql.SET("has_pk = #{hasPk,jdbcType=INTEGER}");
        }
        if (record.getUserName() != null) {
            sql.SET("user_name = #{userName,jdbcType=VARCHAR}");
        }
        if (record.getUserStatus() != null) {
            sql.SET("user_status = #{userStatus,jdbcType=INTEGER}");
        }
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        if (record.getAppId() != null) {
            sql.SET("app_id = #{appId,jdbcType=VARCHAR}");
        }
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        return sql.toString();
    }
}
