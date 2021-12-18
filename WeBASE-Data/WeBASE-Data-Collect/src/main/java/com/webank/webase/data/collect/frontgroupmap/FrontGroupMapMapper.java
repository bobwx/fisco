/**
 * Copyright 2014-2020 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.data.collect.frontgroupmap;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.webank.webase.data.collect.frontgroupmap.entity.FrontGroup;
import com.webank.webase.data.collect.frontgroupmap.entity.MapListParam;
import com.webank.webase.data.collect.frontgroupmap.entity.TbFrontGroupMap;
import java.util.List;

@Repository
public interface FrontGroupMapMapper {

    int add(TbFrontGroupMap tbFrontGroupMap);

    int getCount(MapListParam mapListParam);

    int removeByChainId(@Param("chainId") Integer chainId);

    int removeByFrontId(@Param("frontId") Integer frontId);

    int removeByGroupId(@Param("chainId") Integer chainId, @Param("groupId") Integer groupId);

    List<FrontGroup> getList(MapListParam mapListParam);

    void removeInvalidMap();
}
