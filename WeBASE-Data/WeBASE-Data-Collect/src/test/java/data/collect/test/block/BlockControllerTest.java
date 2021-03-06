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
package data.collect.test.block;

import com.webank.webase.data.collect.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class BlockControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetBlockList() throws Exception {

        String queryByblockList = "/block/list/1/1/1";
        String queryByblockNumber = "/block/list/1/1/15?blockNumber=10";
        String queryByblockHash =
                "/block/list/1/1/15?pkHash=0xca84147e343acb972dc9247727b920b5c081320bbe940f4e2b24363836dca4a1";

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(queryByblockList));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println("=================================response:"
                + resultActions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void testBlockByNumber() throws Exception {
        ResultActions resultActions =
                mockMvc.perform(MockMvcRequestBuilders.get("/block/blockByNumber/1/11"));
        resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
        System.out.println("=================================response:"
                + resultActions.andReturn().getResponse().getContentAsString());
    }

}
