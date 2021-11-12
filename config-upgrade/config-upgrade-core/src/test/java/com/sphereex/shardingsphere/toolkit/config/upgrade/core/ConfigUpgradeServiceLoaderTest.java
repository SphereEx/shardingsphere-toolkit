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

import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereSeries;
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.ShardingSphereSingleSeriesConfigUpgrade;
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.impl.ShardingSphereV3ToV4ConfigUpgrade;
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.impl.ShardingSphereV4ToV5ConfigUpgrade;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public final class ConfigUpgradeServiceLoaderTest {
    
    @Test
    public void assertLoad() {
        Map<ShardingSphereSeries, ShardingSphereSingleSeriesConfigUpgrade> actual = ConfigUpgradeServiceLoader.loadSingleSeriesConfigUpgradeImpl();
        assertNotNull(actual);
        assertTrue(actual.size() > 0);
        ShardingSphereSingleSeriesConfigUpgrade v3ConfigUpgrade = actual.get(ShardingSphereSeries.V3);
        assertNotNull(v3ConfigUpgrade);
        assertTrue(v3ConfigUpgrade instanceof ShardingSphereV3ToV4ConfigUpgrade);
        ShardingSphereSingleSeriesConfigUpgrade v4ConfigUpgrade = actual.get(ShardingSphereSeries.V4);
        assertNotNull(v4ConfigUpgrade);
        assertTrue(v4ConfigUpgrade instanceof ShardingSphereV4ToV5ConfigUpgrade);
    }
}
