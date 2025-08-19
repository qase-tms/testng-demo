# TestNG-Qase-Demo

This repository demonstrates running **TestNG tests using Java**, while using Maven for dependency management and compilation.

All project dependencies are specified in the `pom.xml` file and managed via Maven.  

 **Why `.jar` files in `libs/`?**  
To simplify the runtime execution command and avoid very long classpaths, all dependencies from the `pom.xml` have also been extracted into `.jar` files and placed in the `libs/` folder. This allows tests to run using a straightforward Java command without needing Maven at runtime, while still keeping Maven for compilation and dependency management.

---

## Running Tests

Compile the project using Maven:

```bash
mvn clean compile
```

Run the tests using the following command:

```bash
java \
  -javaagent:$HOME/.m2/repository/org/aspectj/aspectjweaver/1.9.22/aspectjweaver-1.9.22.jar \
  -cp "target/classes:target/test-classes:libs/*" \
  com.example.base.TestRunner
```

### Notes

- The `-javaagent` option is required for proper AspectJ weaving, which ensures that the Qase TestNG listener captures step-level details correctly.  
- The `libs/*` part in the classpath ensures all dependencies are included without needing Maven at runtime.