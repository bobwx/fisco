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
package com.webank.webase.data.collect.receipt;

import com.webank.webase.data.collect.receipt.entity.TbReceipt;
import com.webank.webase.data.collect.transaction.entity.TransListParam;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * mapper about trans receipt.
 */
@Repository
public interface ReceiptMapper {

    int add(@Param("tableName") String tableName, @Param("trans") TbReceipt tbReceipt);

    int getCount(@Param("tableName") String tableName, @Param("param") TransListParam param);

    TbReceipt getByHash(@Param("tableName") String tableName, @Param("transHash") String transHash);

    List<String> listOfUnStatTransHash(@Param("tableName") String tableName);

    List<TbReceipt> getList(@Param("tableName") String tableName,
            @Param("param") TransListParam param);

    int remove(@Param("tableName") String tableName, @Param("subTransNum") Integer subTransNum,
            @Param("groupId") Integer groupId);

    void rollback(@Param("tableName") String tableName, @Param("blockNumber") long blockNumber);
}
