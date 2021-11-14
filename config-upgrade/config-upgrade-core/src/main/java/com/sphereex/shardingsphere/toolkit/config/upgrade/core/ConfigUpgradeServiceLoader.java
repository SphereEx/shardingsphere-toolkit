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
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.SingleSeriesConfigUpgrade;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;

/**
 * Config upgrade service loader.
 */
public final class ConfigUpgradeServiceLoader {
    
    /**
     * Load single series config upgrade implementation.
     *
     * @return implementation grouped by series
     */
    public static Map<ShardingSphereSeries, SingleSeriesConfigUpgrade> loadSingleSeriesConfigUpgradeImpl() {
        ServiceLoader<SingleSeriesConfigUpgrade> serviceLoader = ServiceLoader.load(SingleSeriesConfigUpgrade.class);
        Iterator<SingleSeriesConfigUpgrade> configUpgradeIterator = serviceLoader.iterator();
        Map<ShardingSphereSeries, SingleSeriesConfigUpgrade> result = new HashMap<>();
        while (configUpgradeIterator.hasNext()) {
            SingleSeriesConfigUpgrade configUpgrade = configUpgradeIterator.next();
            ShardingSphereSeries series = configUpgrade.getSourceSeries();
            SingleSeriesConfigUpgrade replaced = result.put(series, configUpgrade);
            if (null != replaced) {
                throw new RuntimeException(String.format("series %s config upgrade impl is replaced, old: %s, new: %s", series, replaced, configUpgrade));
            }
        }
        return result;
    }
}
