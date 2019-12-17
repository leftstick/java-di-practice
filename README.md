# java-di 小练习

观察 `ViewServiceWithNewTest.java` 和 `ViewServiceTest.java` 两个测试用例，他们测试的是一模一样的功能 `ViewService2.java` 和 `ViewService.java`。

区别在于 `ViewService2.java` 里对 `UserService.java` 的引用方式是 `new`。 `ViewService.java` 里对 `UserService.java` 的引用方式是依赖注入。

让后试试看能否在不改源码的情况下使 `ViewServiceWithNewTest.java` 里的测试用例通过
