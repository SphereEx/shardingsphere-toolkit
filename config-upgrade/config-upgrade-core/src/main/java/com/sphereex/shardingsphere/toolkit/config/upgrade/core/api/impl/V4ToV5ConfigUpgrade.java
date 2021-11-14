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

package com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.impl;

import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereSeries;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.config.SeriesConfigItems;
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.api.SingleSeriesConfigUpgrade;
import lombok.extern.slf4j.Slf4j;

/**
 * ShardingSphere V4 to V5 config upgrade.
 */
@Slf4j
public final class V4ToV5ConfigUpgrade implements SingleSeriesConfigUpgrade {
    
    @Override
    public ShardingSphereSeries getSourceSeries() {
        return ShardingSphereSeries.V4;
    }
    
    @Override
    public SeriesConfigItems upgrade(final SeriesConfigItems oldConfigItems) {
        log.info("upgrade, oldConfigItems={}", oldConfigItems);
        // TODO v4 to v5 upgrade
        return new SeriesConfigItems(getSourceSeries().next());
    }
}
