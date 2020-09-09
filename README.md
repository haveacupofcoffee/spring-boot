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
[01-hello-world](https://github.com/haveacupofcoffee/spring-boot/tree/master/01-hello-world)

# 3. Configuration File
## 3.1 Global Configuration File
- application.properties
- application.yml

Function: 

**Change the default configuration values.Global configuration file**

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
[02-spring-boot-config](https://github.com/haveacupofcoffee/spring-boot/tree/master/02-spring-boot-config)

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
[02-spring-boot-config](https://github.com/haveacupofcoffee/spring-boot/tree/master/02-spring-boot-config)
|   | @ConfigurationProperties  | @Value  |
|---|---|---|
| Batch Import| Yes  | No  |
| Relaxed binding  | Yes  | No  |
| Meta-data support  | Yes  | No  |
| SpEL  | No  | Yes  |
| JSR303  | Yes  | No  |

## 3.5 @PropertySource & @ImportResource
@PropertySource: include properties into the Environment  
@ImportResource: Indicates one or more resources containing bean definitions to import  
[02-spring-boot-config02](https://github.com/haveacupofcoffee/spring-boot/tree/master/02-spring-boot-config02)

## 3.6 Randomization & Placeholder
[02-spring-boot-config03](https://github.com/haveacupofcoffee/spring-boot/tree/master/02-spring-boot-config03)

## 3.7 Profile
### 3.7.1 Multiple Profiles
[02-spring-boot-config04-profiles](https://github.com/haveacupofcoffee/spring-boot/tree/master/02-spring-boot-config04-profiles)  
available names: application-{profile}.properties/yml  
default: application.properties.
With yml 
```yaml
server:
  port: 8080
spring:
  profiles:
    active: prod
---
server:
  port: 8081
spring:
  profiles: dev
---
server:
  port: 8082
spring:
  profiles: prod
```
### 3.7.2 Active Profiles
- In configuration file: spring.profiles.active=dev
- command: java -jar ***.jar --spring.profiles.active=dev
- JVM parameter: -Dspring.profiles.active=dev

### 3.7.3 Profiles Location
- file:./config/
- file:./
- classpath:/config/
- classpath:/

change configuration file location with : --spring.config.location

# 4.Behind the scene of AutoConfig
[common-application-properties]https://docs.spring.io/spring-boot/docs/2.3.3.RELEASE/reference/htmlsingle/#common-application-properties

**1.@SpringBootApplication enabled @EnableAutoConfiguration**
**2.@EnableAutoConfiguration uses @Import({AutoConfigurationImportSelector.class}) to import configuration components(classes)**
**3.In AutoConfigurationImportSelector class has a method selectImports()**
```java
 public String[] selectImports(AnnotationMetadata annotationMetadata) {
        if (!this.isEnabled(annotationMetadata)) {
            return NO_IMPORTS;
        } else {
            AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader.loadMetadata(this.beanClassLoader);
            AutoConfigurationImportSelector.AutoConfigurationEntry autoConfigurationEntry = this.getAutoConfigurationEntry(autoConfigurationMetadata, annotationMetadata);
            return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
        }
    }
```
**4.selectImports() calls  this.getAutoConfigurationEntry()**

```java
protected AutoConfigurationImportSelector.AutoConfigurationEntry getAutoConfigurationEntry(AutoConfigurationMetadata autoConfigurationMetadata, AnnotationMetadata annotationMetadata) {
        if (!this.isEnabled(annotationMetadata)) {
            return EMPTY_ENTRY;
        } else {
            AnnotationAttributes attributes = this.getAttributes(annotationMetadata);
            List<String> configurations = this.getCandidateConfigurations(annotationMetadata, attributes);
            configurations = this.removeDuplicates(configurations);
            Set<String> exclusions = this.getExclusions(annotationMetadata, attributes);
            this.checkExcludedClasses(configurations, exclusions);
            configurations.removeAll(exclusions);
            configurations = this.filter(configurations, autoConfigurationMetadata);
            this.fireAutoConfigurationImportEvents(configurations, exclusions);
            return new AutoConfigurationImportSelector.AutoConfigurationEntry(configurations, exclusions);
        }
    }
```
**5.getAutoConfigurationEntry() calls this.getCandidateConfigurations()**
```java
protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
        List<String> configurations = SpringFactoriesLoader.loadFactoryNames(this.getSpringFactoriesLoaderFactoryClass(), this.getBeanClassLoader());
        Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring.factories. If you are using a custom packaging, make sure that file is correct.");
        return configurations;
    }
```

**6.getCandidateConfigurations calls SpringFactoriesLoader.loadFactoryNames()**
```java
    public static List<String> loadFactoryNames(Class<?> factoryType, @Nullable ClassLoader classLoader) {
        String factoryTypeName = factoryType.getName();
        return (List)loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList());
    }
```
The first parameter factoryType is passed by this.getSpringFactoriesLoaderFactoryClass() in step 5, which is
```java
    protected Class<?> getSpringFactoriesLoaderFactoryClass() {
        return EnableAutoConfiguration.class;
    }
```   

**7.loadFactoryNames() calls loadSpringFactories()**  
Which checks cache first, if no result in cache, then look for properties configuration in "META-INF/spring.factories" 
that is mapped with **EnableAutoConfiguration**
```java
private static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
        MultiValueMap<String, String> result = (MultiValueMap)cache.get(classLoader);
        if (result != null) {
            return result;
        } else {
            try {
                Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
                LinkedMultiValueMap result = new LinkedMultiValueMap();

                while(urls.hasMoreElements()) {
                    URL url = (URL)urls.nextElement();
                    UrlResource resource = new UrlResource(url);
                    Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                    Iterator var6 = properties.entrySet().iterator();

                    while(var6.hasNext()) {
                        Entry<?, ?> entry = (Entry)var6.next();
                        String factoryTypeName = ((String)entry.getKey()).trim();
                        String[] var9 = StringUtils.commaDelimitedListToStringArray((String)entry.getValue());
                        int var10 = var9.length;

                        for(int var11 = 0; var11 < var10; ++var11) {
                            String factoryImplementationName = var9[var11];
                            result.add(factoryTypeName, factoryImplementationName.trim());
                        }
                    }
                }

                cache.put(classLoader, result);
                return result;
            } catch (IOException var13) {
                throw new IllegalArgumentException("Unable to load factories from location [META-INF/spring.factories]", var13);
            }
        }
    }
```
In spring-boot-autoconfigure-2.2.9.RELEASE.jar/META-INFO/spring.factories
```yaml
# Auto Configure
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
```

Use debug=true to print   
= ========================  
AUTO‐CONFIGURATION REPORT  
= ========================    
