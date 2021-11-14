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

import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ConfigUpgradeParameter;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereProductType;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereVersion;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.config.SeriesConfigItems;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public final class MultipleSeriesConfigUpgradeTest {
    
    @Test
    public void assertUpgrade1() {
        ShardingSphereProductType productType = ShardingSphereProductType.PROXY;
        ShardingSphereVersion sourceVersion = ShardingSphereVersion.V4_0_0;
        ShardingSphereVersion targetVersion = ShardingSphereVersion.V5_0_0;
        SeriesConfigItems sourceConfigItems = new SeriesConfigItems(sourceVersion.getSeries(), productType);
        ConfigUpgradeParameter parameter = new ConfigUpgradeParameter(productType, sourceVersion, targetVersion, sourceConfigItems);
        MultipleSeriesConfigUpgrade configUpgrade = new MultipleSeriesConfigUpgrade();
        SeriesConfigItems targetConfigItems = configUpgrade.upgrade(parameter);
        assertNotNull(targetConfigItems);
        assertThat(targetConfigItems.getSeries(), is(targetVersion.getSeries()));
    }
}
