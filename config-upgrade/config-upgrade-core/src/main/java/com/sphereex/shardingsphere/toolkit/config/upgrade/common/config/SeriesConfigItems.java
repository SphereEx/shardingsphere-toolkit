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

package com.sphereex.shardingsphere.toolkit.config.upgrade.common.config;

import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereSeries;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Config items of series.
 */
@RequiredArgsConstructor
public final class SeriesConfigItems {
    
    @Getter
    @NonNull
    private final ShardingSphereSeries series;
    
    private final List<SeriesConfigItem> configItemList = new ArrayList<>();
    
    /**
     * Get read only config item list.
     *
     * @return read only config item list
     */
    public List<SeriesConfigItem> getReadOnlyConfigItemList() {
        return Collections.unmodifiableList(configItemList);
    }
    
    /**
     * Add config item.
     *
     * @param configItem config item
     */
    public void addConfigItem(final SeriesConfigItem configItem) {
        configItemList.add(configItem);
    }
    
    @Override
    public String toString() {
        return "SeriesConfigItems{" + "series=" + series + ", configItemList=" + configItemList + '}';
    }
}
