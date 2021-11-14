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

package com.sphereex.shardingsphere.toolkit.config.upgrade.bootstrap;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ConfigUpgradeParameter;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.config.SeriesConfigItem;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.config.SeriesConfigItems;
import com.sphereex.shardingsphere.toolkit.config.upgrade.core.MultipleSeriesConfigUpgrade;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

/**
 * ShardingSphere config upgrade bootstrap.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class ConfigUpgradeBootstrap {
    
    /**
     * Main entrance.
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        ConfigUpgradeCommandArguments commandArguments = new ConfigUpgradeCommandArguments();
        JCommander commander = JCommander.newBuilder().addObject(commandArguments).build();
        try {
            commander.parse(args);
        } catch (final ParameterException ex) {
            log.error("Parse command arguments failed: {}", ex.getMessage());
            return;
        }
        if (commandArguments.isHelp()) {
            commander.usage();
            return;
        }
        ConfigUpgradeCommandArguments.Context context = ConfigUpgradeCommandArguments.Context.convert(commandArguments);
        log.info("command arguments: {}", commandArguments);
        if (!context.isValid()) {
            return;
        }
        log.info("source files: {}", Arrays.asList(context.getSourceFiles()));
        File targetConfDir = context.getTargetConfDir();
        if (!targetConfDir.exists() && !targetConfDir.mkdirs()) {
            log.error("Create target conf dir failed, {}", targetConfDir.getAbsolutePath());
            return;
        }
        SeriesConfigItems sourceConfigItems = new SeriesConfigItems(context.getSourceVersion().getSeries());
        for (File each : context.getSourceFiles()) {
            String content;
            try {
                content = new String(Files.readAllBytes(each.toPath()), context.getConfFileEncoding());
            } catch (final IOException ex) {
                log.error("Read content from {} failed", each.getAbsolutePath(), ex);
                return;
            }
            sourceConfigItems.addConfigItem(new SeriesConfigItem(each.getName(), content));
        }
        ConfigUpgradeParameter configUpgradeParameter = new ConfigUpgradeParameter(context.getProductType(), context.getSourceVersion(), context.getTargetVersion(), sourceConfigItems);
        MultipleSeriesConfigUpgrade configUpgrade = new MultipleSeriesConfigUpgrade();
        SeriesConfigItems targetConfigItems = configUpgrade.upgrade(configUpgradeParameter);
        for (SeriesConfigItem each : targetConfigItems.getReadOnlyConfigItemList()) {
            File targetFile = new File(targetConfDir, each.getName());
            try {
                Files.write(targetFile.toPath(), each.getContent().getBytes(context.getConfFileEncoding()));
            } catch (final IOException ex) {
                log.error("Write content to {} failed", targetFile.getAbsolutePath(), ex);
                return;
            }
        }
        log.info("Config upgrade is completed. Please check {}", targetConfDir.getAbsolutePath());
    }
    
}
