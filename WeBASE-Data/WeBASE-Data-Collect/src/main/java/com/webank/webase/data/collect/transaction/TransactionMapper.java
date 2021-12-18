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
package com.webank.webase.data.collect.transaction;

import com.webank.webase.data.collect.block.entity.MinMaxBlock;
import com.webank.webase.data.collect.transaction.entity.TbTransaction;
import com.webank.webase.data.collect.transaction.entity.TransListParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * mapper about trans hash.
 */
@Repository
public interface TransactionMapper {

    Integer add(@Param("tableName") String tableName, @Param("trans") TbTransaction tbTransaction);

    Integer getCount(@Param("tableName") String tableName, @Param("param") TransListParam param);

    Integer getCountByMinMax(@Param("tableName") String tableName);

    TbTransaction getByHash(@Param("tableName") String tableName,
            @Param("transHash") String transHash);

    List<TbTransaction> getList(@Param("tableName") String tableName,
            @Param("param") TransListParam param);

    List<MinMaxBlock> queryMinMaxBlock(@Param("tableName") String tableName);

    List<String> listOfUnStatTransHash(@Param("tableName") String tableName);

    void updateTransStatFlag(@Param("tableName") String tableName,
            @Param("transHash") String transHash);

    void rollback(@Param("tableName") String tableName, @Param("blockNumber") long blockNumber);
}
