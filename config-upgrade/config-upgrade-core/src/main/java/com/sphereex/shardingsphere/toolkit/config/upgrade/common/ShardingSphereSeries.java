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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * ShardingSphere series.
 * <p>
 * It should be in order, from old series to new series.
 * </p>
 */
public enum ShardingSphereSeries {
    
    V3,
    V4,
    V5;
    
    private static final Map<ShardingSphereSeries, ShardingSphereSeries> NEXT_MAP;
    
    static {
        Map<ShardingSphereSeries, ShardingSphereSeries> map = new HashMap<>();
        Iterator<ShardingSphereSeries> seriesIterator = Arrays.asList(values()).iterator();
        ShardingSphereSeries current = seriesIterator.next();
        ShardingSphereSeries next;
        while (seriesIterator.hasNext()) {
            next = seriesIterator.next();
            map.put(current, next);
            current = next;
        }
        if (!map.containsKey(current)) {
            map.put(current, null);
        }
        NEXT_MAP = map;
    }
    
    /**
     * Get next series.
     *
     * @return next series
     */
    public ShardingSphereSeries next() {
        return NEXT_MAP.get(this);
    }
}
