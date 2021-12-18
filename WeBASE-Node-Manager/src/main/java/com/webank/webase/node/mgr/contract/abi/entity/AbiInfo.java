/**
 * Copyright 2014-2021 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webank.webase.node.mgr.contract.abi.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * entity for table tb_abi to store raw contract abi
 */
@Data
@NoArgsConstructor
public class AbiInfo {
	private Integer abiId;
	private Integer groupId;
	private String account;
	private String contractName;
	private String contractAddress;
	private String contractAbi;
	/**
	 * runtime bin
	 */
	private String contractBin;
	/**
	 * 1-normal, 2-invalid
	 */
	// private Integer contractStatus;
	private LocalDateTime createTime;
	private LocalDateTime modifyTime;

}

