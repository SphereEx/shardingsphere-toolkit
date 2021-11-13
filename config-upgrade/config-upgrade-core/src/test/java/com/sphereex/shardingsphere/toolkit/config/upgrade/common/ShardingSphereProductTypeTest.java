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

public final class ShardingSphereProductTypeTest {
    
    @Test
    public void assertValueOfByName() {
        Collection<Pair<String, ShardingSphereProductType>> dataSet = Arrays.asList(
                Pair.of("jdbc", ShardingSphereProductType.JDBC), Pair.of("JDBC", ShardingSphereProductType.JDBC),
                Pair.of("proxy", ShardingSphereProductType.PROXY), Pair.of("PROXY", ShardingSphereProductType.PROXY)
        );
        for (Pair<String, ShardingSphereProductType> each : dataSet) {
            assertThat(ShardingSphereProductType.valueOfByName(each.getLeft()), is(each.getRight()));
        }
    }
}
