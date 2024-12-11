# dormitory-mangerment-system
数据库相关
```
<environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///yourtablename?useSSL=false&amp;useServerPrepStmts=true&amp;allowPublicKeyRetrieval=true"/>
                <property name="username" value="yourname"/>
                <property name="password" value="yourpassword"/>
            </dataSource>
        </environment>
    </environments>
