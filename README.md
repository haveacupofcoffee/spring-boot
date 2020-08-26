# 1. POM 

```xml
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

web starter, it's parent: 

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-parent</artifactId>
  <version>2.2.9.RELEASE</version>
  <relativePath/> <!-- lookup parent from repository -->
</parent>

```

it's parent: 

```xml
<parent>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-dependencies</artifactId>
  <version>2.2.9.RELEASE</version>
  <relativePath>../../spring-boot-dependencies</relativePath>
</parent>
```

**This is the pom file  providing dependency and plugin management for applications built with Maven**

# 2. HelloWorld
[comment]: # (add source file)

# 3. Configuration File
## 3.1 Global Configuration File
- application.properties
- application.yml

Function: 

**Change the default configuration values.Global configuration file, =**

## 3.2 configuration File format

Before using ****.xml file, now using .properties or .yml

## 3.3 YAML

### 3.3.1 Syntax
```yaml
server:
  port: 8080
  path: /hello
```
**Note:**
- Must have space after :
- Uses indentation to inicate a block of object or level
- Case sensitive

### 3.3.2 How to write

#### 3.3.2.1 Primary Type

```
Key: Value
```
Don't need "" or ''

"" Example:
```yaml
welcome: "Hello\nWorld"
```
Output:
```
Hello
World
```
''Example:
```yaml
welcome: 'Hello\nWorld'
```
Output:
```
Hello\nWorld
```

#### 3.3.2.2 Object、 Map

Example:
```yaml
person: 
  firstName: hello
  lastName: world
  age: 20
```
inline:
```yaml
person: {firstName: hello,lastname: world,age: 20}
```

#### 3.3.2.3 List、 Set

Example:
```yaml
pets:
  - cat
  - dog
  - bird
```
inline:
```yaml
pets: [cat,dog,pig]
```
## 3.4 Configuration File Injection

application.yml
```yaml
person:
  firstName: hello
  lastName: world
  age: 20
  boss: false
  birth: 2020/08/11
  maps: {k1: v1,k2: v2}
  lists:
    ‐ react
    ‐ springboot
  pet:
    name: snowball
    age: 2
```

### 3.4.1 @ConfigurationProperties vs @Value
|   | @ConfigurationProperties  | @Value  |
|---|---|---|
| Batch Import| Yes  | No  |
| Relaxed binding  | Yes  | No  |
| Meta-data support  | Yes  | No  |
| SpEL  | No  | Yes  |
| JSR303  | Yes  | No  |