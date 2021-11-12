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

package com.sphereex.shardingsphere.toolkit.config.upgrade.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ShardingSphere version enum.
 * <p>
 * It should be in order, from old version to new version.
 * </p>
 */
public enum ShardingSphereVersion {
    
    V4_0_0("4.0.0", ShardingSphereSeries.V4),
    V4_1_0("4.1.0", ShardingSphereSeries.V4),
    V4_1_1("4.1.1", ShardingSphereSeries.V4),
    V5_0_0("5.0.0", ShardingSphereSeries.V5);
    
    private static final Map<String, ShardingSphereVersion> NAME_VERSION_MAP;
    
    static {
        Map<String, ShardingSphereVersion> map = new HashMap<>();
        for (ShardingSphereVersion each : ShardingSphereVersion.values()) {
            map.put(each.getName(), each);
        }
        NAME_VERSION_MAP = map;
    }
    
    private final String name;
    
    private final ShardingSphereSeries series;
    
    ShardingSphereVersion(final String name, final ShardingSphereSeries series) {
        Objects.requireNonNull(name, "name is null");
        Objects.requireNonNull(series, "series is null");
        this.name = name;
        this.series = series;
    }
    
    /**
     * Get name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Get series.
     *
     * @return series
     */
    public ShardingSphereSeries getSeries() {
        return series;
    }
    
    /**
     * Get {@link ShardingSphereVersion} by name.
     *
     * @param name name
     * @return shardingsphere version
     */
    public static ShardingSphereVersion valueOfByName(final String name) {
        return NAME_VERSION_MAP.get(name);
    }
    
    @Override
    public String toString() {
        return String.format("%s{name=%s,series=%s}", name(), name, series);
    }
}
