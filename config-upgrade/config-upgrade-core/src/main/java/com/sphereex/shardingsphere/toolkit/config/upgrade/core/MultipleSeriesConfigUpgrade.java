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

import com.google.common.base.Preconditions;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ConfigUpgradeParameter;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereSeries;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereVersion;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.config.SeriesConfigItems;
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.SingleSeriesConfigUpgrade;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * ShardingSphere multiple series config upgrade.
 */
@Slf4j
public final class MultipleSeriesConfigUpgrade {
    
    /**
     * Upgrade.
     *
     * @param parameter config upgrade parameter
     * @return target series config items
     */
    public SeriesConfigItems upgrade(final ConfigUpgradeParameter parameter) {
        log.info("upgrade, parameter={}", parameter);
        Preconditions.checkNotNull(parameter, "parameter is null");
        ShardingSphereVersion sourceVersion = parameter.getSourceVersion();
        ShardingSphereVersion targetVersion = parameter.getTargetVersion();
        Preconditions.checkState(sourceVersion.ordinal() < targetVersion.ordinal(), "source version must be less than target version");
        List<ShardingSphereSeries> seriesUpgradePath = sourceVersion.getSeries().getSeriesUpgradePath(targetVersion.getSeries());
        log.info("seriesUpgradePath={}", seriesUpgradePath);
        Preconditions.checkState(seriesUpgradePath.size() > 0, "seriesUpgradePath is empty");
        Map<ShardingSphereSeries, SingleSeriesConfigUpgrade> seriesConfigUpgradeMap = ConfigUpgradeServiceLoader.loadSingleSeriesConfigUpgradeImpl();
        Iterator<ShardingSphereSeries> seriesIterator = seriesUpgradePath.iterator();
        SeriesConfigItems currentConfigItems = parameter.getSourceConfigItems();
        while (seriesIterator.hasNext()) {
            ShardingSphereSeries currentSeries = seriesIterator.next();
            SingleSeriesConfigUpgrade configUpgrade = seriesConfigUpgradeMap.get(currentSeries);
            log.info("currentSeries={}, configUpgrade={}", currentSeries, configUpgrade);
            SeriesConfigItems nextConfigItems = configUpgrade.upgrade(currentConfigItems);
            log.info("nextConfigItems={}", nextConfigItems);
            currentConfigItems = nextConfigItems;
        }
        return currentConfigItems;
    }
}
