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
package com.webank.webase.data.collect.node;

import com.webank.webase.data.collect.node.entity.NodeParam;
import com.webank.webase.data.collect.node.entity.OrgInfo;
import com.webank.webase.data.collect.node.entity.TbNode;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * node data interface.
 */
@Repository
public interface NodeMapper {

    /**
     * Add new node data.
     */
    Integer add(TbNode tbNode);
    
    /**
     * update node org info.
     */
    Integer updateOrgInfo(OrgInfo orgInfo);

    /**
     * Query the number of node according to some conditions.
     */
    Integer getCount(NodeParam nodeParam);

    /**
     * Query node list according to some conditions.
     */
    List<TbNode> getList(NodeParam nodeParam);
    
    /**
     * Query the number of org node according to some conditions.
     */
    Integer getOrgNodeCount(NodeParam nodeParam);
    
    /**
     * Query org node list according to some conditions.
     */
    List<OrgInfo> getOrgNodeList(NodeParam nodeParam);

    /**
     * query tb_node by nodeip and p2pport.
     */
    TbNode queryNodeByIpAndP2pPort(@Param("nodeIp") String nodeIp,
            @Param("p2pPort") Integer p2pPort);

    /**
     * query node info.
     */
    TbNode queryByNodeId(@Param("nodeId") String nodeId);

    /**
     * update node info.
     */
    Integer update(TbNode dbNode);

    /**
     * query node info.
     */
    TbNode queryNodeInfo(NodeParam nodeParam);


    /**
     * delete by nodeId and groupId.
     */
    Integer deleteByNodeAndGroup(@Param("nodeId") String nodeId, @Param("groupId") Integer groupId);

    /**
     * delete by groupId.
     */
    Integer deleteByGroupId(@Param("chainId") Integer chainId, @Param("groupId") Integer groupId);

    /**
     * delete by chainId.
     */
    Integer deleteByChainId(@Param("chainId") Integer chainId);
}
