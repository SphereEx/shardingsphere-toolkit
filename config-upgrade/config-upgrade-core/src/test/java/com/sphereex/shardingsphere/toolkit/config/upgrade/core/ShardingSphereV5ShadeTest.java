/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sphereex.shardingsphere.toolkit.config.upgrade.core;

import org.apache.shardingsphere.v5.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.v5.sharding.yaml.config.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.v5.sharding.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public final class ShardingSphereV5ShadeTest {
    
    @Test
    public void assertShade() {
        YamlShardingRuleConfiguration yamlConfig = new YamlShardingRuleConfiguration();
        yamlConfig.setBindingTables(Collections.singletonList("t_order"));
        ShardingRuleConfiguration config = new ShardingRuleConfigurationYamlSwapper().swapToObject(yamlConfig);
        Collection<String> actual = config.getBindingTableGroups();
        assertNotNull(actual);
        assertThat(actual.size(), is(1));
        assertThat(actual.iterator().next(), is("t_order"));
    }
}
