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

package com.sphereex.shardingsphere.toolkit.config.upgrade.core.api;

import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereSeries;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.config.SeriesConfigItems;

/**
 * ShardingSphere single series config upgrade SPI interface.
 */
public interface SingleSeriesConfigUpgrade {
    
    /**
     * Get source series.
     *
     * @return source series
     */
    ShardingSphereSeries getSourceSeries();
    
    /**
     * Upgrade.
     *
     * @param oldConfigItems old series config items
     * @return new series config items
     */
    SeriesConfigItems upgrade(SeriesConfigItems oldConfigItems);
}
