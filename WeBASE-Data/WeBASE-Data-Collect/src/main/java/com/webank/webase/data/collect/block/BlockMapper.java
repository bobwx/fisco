/**
 * Copyright 2014-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.data.collect.block;

import com.webank.webase.data.collect.block.entity.BlockListParam;
import com.webank.webase.data.collect.block.entity.TbBlock;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Block data interface.
 */
@Repository
public interface BlockMapper {

    /**
     * Add new block data.
     */
    Integer add(@Param("tableName") String tableName, @Param("block") TbBlock tbBlock);

    /**
     * query latest block number
     */
    BigInteger getLatestBlockNumber(@Param("tableName") String tableName);

    /**
     * findByBlockNumber.
     */
    TbBlock findByBlockNumber(@Param("tableName") String tableName,
            @Param("blockNumber") long blockNumber);

    /**
     * query list of block by page.
     */
    List<TbBlock> getList(@Param("tableName") String tableName,
            @Param("param") BlockListParam param);

    /**
     * query block count.
     */
    Integer getCount(@Param("tableName") String tableName, @Param("blockHash") String blockHash,
            @Param("blockNumber") BigInteger blockNumber);

    /**
     * get block count by max minux min
     */
    Integer getBlockCountByMinMax(@Param("tableName") String tableName);

    /**
     * Delete block height.
     */
    Integer remove(@Param("tableName") String tableName,
            @Param("blockRetainMax") BigInteger blockRetainMax);

    /**
     * rollback.
     */
    void rollback(@Param("tableName") String tableName, @Param("blockNumber") long blockNumber);
}
