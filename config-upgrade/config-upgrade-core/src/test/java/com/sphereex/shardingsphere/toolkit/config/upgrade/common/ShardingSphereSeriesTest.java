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

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class ShardingSphereSeriesTest {
    
    @Test
    public void assertNext() {
        Collection<Pair<ShardingSphereSeries, ShardingSphereSeries>> testSet = Arrays.asList(Pair.of(ShardingSphereSeries.V3, ShardingSphereSeries.V4),
                Pair.of(ShardingSphereSeries.V4, ShardingSphereSeries.V5), Pair.of(ShardingSphereSeries.V5, null));
        for (Pair<ShardingSphereSeries, ShardingSphereSeries> each : testSet) {
            assertThat(each.getLeft().next(), is(each.getRight()));
        }
    }
}
