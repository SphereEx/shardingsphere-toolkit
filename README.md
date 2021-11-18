# Toolkit
Apache ShardingSphere toolkit

## Features

### Config upgrade
Convert old series configuration to new series.

Supported single series upgrade:
- v4 to v5 (Under development)
- v3 to v4 (Under development)

Supported multiple series upgrade:
- Any sequential single series upgrade combination, e.g. v3 to v5

Series and versions mapping:

| *Series*         | *Versions*                                  |
| ---------------- | ------------------------------------------- |
| v5               | 5.0.0                                       |
| v4               | 4.0.0, 4.0.1, 4.1.0, 4.1.1                  |

## Usage

1. Download source code
```
git clone --depth=1 https://github.com/SphereEx/shardingsphere-toolkit.git
```

2. Build
```
mvn clean
mvn package
```

3. Run

| *Feature*                    | *Main class*                                | *Executable JAR*                                                   |
| ---------------------------- | ------------------------------------------- | ------------------------------------------------------------------ |
| Config upgrade               | ConfigUpgradeBootstrap                      | config-upgrade-bootstrap-1.0-SNAPSHOT-jar-with-dependencies.jar    |

Run in IDE or run executable jar in target.

e.g.
```
java -jar config-upgrade-bootstrap-1.0-SNAPSHOT-jar-with-dependencies.jar --help
```

