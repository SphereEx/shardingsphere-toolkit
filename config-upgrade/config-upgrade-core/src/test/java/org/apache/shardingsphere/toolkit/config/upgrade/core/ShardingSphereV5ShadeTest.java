package org.apache.shardingsphere.toolkit.config.upgrade.core;

import org.apache.shardingsphere.v5.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.v5.sharding.yaml.config.YamlShardingRuleConfiguration;
import org.apache.shardingsphere.v5.sharding.yaml.swapper.ShardingRuleConfigurationYamlSwapper;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public final class ShardingSphereV5ShadeTest {
    
    @Test
    public void assertShade() {
        YamlShardingRuleConfiguration yamlConfig = new YamlShardingRuleConfiguration();
        yamlConfig.setBindingTables(Collections.singletonList("t_order"));
        ShardingRuleConfiguration config = new ShardingRuleConfigurationYamlSwapper().swapToObject(yamlConfig);
        Collection<String> actual = config.getBindingTableGroups();
        assertNotNull(actual);
        assertThat(actual.size(), is(1));
        assertThat(actual.iterator().next(), is("t_order"));
    }
}
