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
package com.webank.webase.data.collect.scheduler;

import com.webank.webase.data.collect.base.enums.DataStatus;
import com.webank.webase.data.collect.group.GroupService;
import com.webank.webase.data.collect.group.entity.TbGroup;
import com.webank.webase.data.collect.transaction.TransactionService;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * data parser
 * 
 */
@Log4j2
@Component
@ConditionalOnProperty(value = {"constant.multiLiving"}, havingValue = "false")
public class DataParserTask {

    @Autowired
    private GroupService groupService;
    @Autowired
    private TransactionService transactionService;

    @Scheduled(cron = "${constant.dataParserCron}")
    public void taskStart() {
        parserStart();
    }

    /**
     * parserStart.
     */
    public void parserStart() {
        log.info("start parser.");
        Instant startTime = Instant.now();
        List<TbGroup> groupList = groupService.getGroupList(null, DataStatus.NORMAL.getValue());
        if (CollectionUtils.isEmpty(groupList)) {
            log.info("parser jump over: not found any group");
            return;
        }
        // count down group, make sure all group's transMonitor finished
        CountDownLatch latch = new CountDownLatch(groupList.size());
        groupList.stream()
                .forEach(group -> transactionService.parserProcess(latch, group.getChainId(), group.getGroupId()));
        try {
            latch.await();
        } catch (InterruptedException ex) {
            log.error("InterruptedException", ex);
            Thread.currentThread().interrupt();
        }
        log.info("end parser useTime:{} ", Duration.between(startTime, Instant.now()).toMillis());
    }
}
