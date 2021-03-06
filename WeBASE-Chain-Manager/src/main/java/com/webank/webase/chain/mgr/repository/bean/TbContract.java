package com.webank.webase.chain.mgr.repository.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class TbContract implements Serializable {

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_id
     *
     * @mbg.generated
     */
    private Integer contractId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_path
     *
     * @mbg.generated
     */
    private String contractPath;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_name
     *
     * @mbg.generated
     */
    private String contractName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.chain_id
     *
     * @mbg.generated
     */
    private Integer chainId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.group_id
     *
     * @mbg.generated
     */
    private Integer groupId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.save_by_agency
     *
     * @mbg.generated
     */
    private Integer saveByAgency;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_address
     *
     * @mbg.generated
     */
    private String contractAddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.deploy_time
     *
     * @mbg.generated
     */
    private Date deployTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_status
     *
     * @mbg.generated
     */
    private Byte contractStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_type
     *
     * @mbg.generated
     */
    private Byte contractType;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.description
     *
     * @mbg.generated
     */
    private String description;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.create_time
     *
     * @mbg.generated
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.modify_time
     *
     * @mbg.generated
     */
    private Date modifyTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_source
     *
     * @mbg.generated
     */
    private String contractSource;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_abi
     *
     * @mbg.generated
     */
    private String contractAbi;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.contract_bin
     *
     * @mbg.generated
     */
    private String contractBin;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_contract.bytecode_bin
     *
     * @mbg.generated
     */
    private String bytecodeBin;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_contract
     *
     * @mbg.generated
     */
    private static final long serialVersionUID = 1L;
}
