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
package com.webank.webase.data.collect.contract;


import com.webank.webase.data.collect.contract.entity.MethodInfo;
import com.webank.webase.data.collect.contract.entity.TbMethod;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MethodMapper {

    void add(TbMethod tbMethod);

    void removeByContractId(@Param("contractId") Integer contractId);

    void removeByChainIdAndGroupId(@Param("chainId") Integer chainId,
            @Param("groupId") Integer groupId);

    List<MethodInfo> getMethodInfo(@Param("chainId") Integer chainId,
            @Param("groupId") Integer groupId, @Param("methodId") String methodId,
            @Param("contractAddress") String contractAddress, @Param("subBin") String subBin);
}
