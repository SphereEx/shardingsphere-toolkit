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

import com.beust.jcommander.Parameter;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereProductType;
import com.sphereex.shardingsphere.toolkit.config.upgrade.common.ShardingSphereVersion;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Objects;

/**
 * ShardingSphere config upgrade command arguments.
 */
@Data
public final class ConfigUpgradeCommandArguments {
    
    @Parameter(names = "-product-type", description = "ShardingSphere product type, options: JDBC, PROXY.", required = true)
    private String productType;
    
    @Parameter(names = "-source-version", description = "ShardingSphere source version.", required = true)
    private String sourceVersion;
    
    @Parameter(names = "-target-version", description = "ShardingSphere target version.", required = true)
    private String targetVersion;
    
    @Parameter(names = "-source-conf-dir", description = "ShardingSphere source configuration directory.", required = true)
    private String sourceConfDir;
    
    @Parameter(names = "-target-conf-dir", description = "ShardingSphere source configuration directory. Should be empty.", required = true)
    private String targetConfDir;
    
    @Parameter(names = "-conf-file-encoding", description = "ShardingSphere configuration file encoding, default value: UTF8.")
    private String confFileEncoding;
    
    @Parameter(names = {"--help", "-h"}, description = "Show this usage", help = true)
    private boolean help;
    
    @Getter
    @Setter
    @Slf4j
    public static final class Context {
        
        private ShardingSphereProductType productType;
        
        private ShardingSphereVersion sourceVersion;
        
        private ShardingSphereVersion targetVersion;
        
        private File sourceConfDir;
        
        private Charset confFileEncoding;
        
        private File targetConfDir;
        
        private boolean valid = true;
    
        /**
         * Listed files from {@link #sourceConfDir}.
         */
        private File[] sourceFiles;
        
        /**
         * Convert command arguments to context.
         *
         * @param commandArguments command arguments
         * @return context
         */
        public static Context convert(final ConfigUpgradeCommandArguments commandArguments) {
            Context result = new Context();
            ShardingSphereProductType productType = ShardingSphereProductType.valueOfByName(commandArguments.getProductType());
            result.setProductType(productType);
            if (null == productType) {
                log.error("Invalid product type: {}", commandArguments.getProductType());
                result.setValid(false);
            }
            ShardingSphereVersion sourceVersion = ShardingSphereVersion.valueOfByName(commandArguments.getSourceVersion());
            result.setSourceVersion(sourceVersion);
            if (null == sourceVersion) {
                log.error("Invalid source version: {}", commandArguments.getSourceVersion());
                result.setValid(false);
            }
            ShardingSphereVersion targetVersion = ShardingSphereVersion.valueOfByName(commandArguments.getTargetVersion());
            result.setTargetVersion(targetVersion);
            if (null == targetVersion) {
                log.error("Invalid target version: {}", commandArguments.getTargetVersion());
                result.setValid(false);
            }
            File sourceConfDir = new File(commandArguments.getSourceConfDir());
            result.setSourceConfDir(sourceConfDir);
            String sourceConfDirPath = sourceConfDir.getAbsolutePath();
            if (!sourceConfDir.exists()) {
                log.error("Invalid source conf dir: {}, it does not exist", sourceConfDirPath);
                result.setValid(false);
            } else if (!sourceConfDir.isDirectory()) {
                log.error("Invalid source conf dir: {}, it is not directory", sourceConfDirPath);
                result.setValid(false);
            } else if (!sourceConfDir.canRead()) {
                log.error("Invalid source conf dir: {}, it can not be read", sourceConfDirPath);
                result.setValid(false);
            } else {
                File[] sourceFiles = sourceConfDir.listFiles();
                result.setSourceFiles(sourceFiles);
                if (null == sourceFiles || sourceFiles.length == 0) {
                    log.error("Invalid source conf dir: {}, list files null or empty", sourceConfDirPath);
                    result.setValid(false);
                }
            }
            File targetConfDir = new File(commandArguments.getTargetConfDir());
            result.setTargetConfDir(targetConfDir);
            String targetConfDirPath = targetConfDir.getAbsolutePath();
            if (targetConfDir.exists()) {
                if (!targetConfDir.isDirectory()) {
                    log.error("Invalid target conf dir: {}, it is not directory", targetConfDirPath);
                    result.setValid(false);
                } else if (!targetConfDir.canRead() || !targetConfDir.canWrite()) {
                    log.error("Invalid target conf dir: {}, it can not be read or written", targetConfDirPath);
                    result.setValid(false);
                } else if (Objects.requireNonNull(targetConfDir.list()).length > 0) {
                    log.error("Invalid target conf dir: {}, it is not empty", targetConfDirPath);
                    result.setValid(false);
                }
            }
            result.setConfFileEncoding(Charset.forName(null != commandArguments.getConfFileEncoding() ? commandArguments.getConfFileEncoding() : "UTF8"));
            return result;
        }
        
        /**
         * Is valid.
         *
         * @return valid or not
         */
        public boolean isValid() {
            return valid;
        }
    }
}
