# centimani(百臂巨人)

A port of Github's refactoring tool [Scientist](https://github.com/github/scientist) in Java

[中文文档](README_CN.md)

# 引用

项目需要自己打包，毕竟美国人民周六日不工作，计划下周发布 `Maven` 仓库 

```java
<dependency>
    <groupId>com.github.zyndev</groupId>
    <artifactId>centimani</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

# 用法

在看《演进式架构》时，看到 `Github` 重构的项目，里面提到了 `Scientist` 这个项目，这个项目的灵感就来自于此。
因为那个项目是 `Ruby` 写的，我也不会用，现在用 `Java` 凑合一下

首先这个是针对项目重构使用的，在重构时我们去检测新的实现和老的实现是否有差异

## 基本使用

基本代码就是这样，先创建一个 `Experiment<T>` 其中 `T` 为对应结果的类型，第二参数为一个比较器，用来比较是否存在差异

```java
Experiment(String name, BiFunction<T, T, Boolean> comparator)
```

**1. 简单对比一下两个值是否相同**

```java
Experiment<Double> experiment = new Experiment<>("测试double", (c1, c2) -> c1.intValue() == c2.intValue());
Result result = experiment.run(() -> 1D, () -> 1D);
System.out.println(result);
Assert.assertTrue(result.isMatch());
```

**2. 对比新老实现功能是否一致**


```java
public interface IReport {
    long getMaxAge();
}

public class OldReportImpl implements IReport {
    @Override
    public long getMaxAge() {
        try {
            // 假设老方法性能堪忧
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

public class NewReportImpl implements IReport {
    @Override
    public long getMaxAge() {
        // 新方法提升了 200 倍性能
        return 0;
    }
}

// 测试一下
@Test
public void testReport() {
    IReport oldReport = new OldReportImpl();
    IReport newReport = new NewReportImpl();

    Experiment<Long> experiment = new Experiment<>("测试报表", (c1, c2) -> c1.intValue() == c2.intValue());
    Result result = experiment.run(oldReport::getMaxAge, newReport::getMaxAge);
    System.out.println(result);
    Assert.assertTrue(result.isMatch());
}
```

**结果为**

好的性能确实提升了，而且还和原行为一致

```java
Result(match=true, controlTime=205, candidateTime=0)
```


License: Apache License
