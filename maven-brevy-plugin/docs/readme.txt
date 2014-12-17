1. 资源移除; 2错误码生成
配置：
<plugin>
	<groupId>com.brevy.maven.plugins</groupId>
	<artifactId>maven-brevy-plugin</artifactId>
	<version>1.0.0</version>
	<configuration>
		<!-- goal为remove的操作配置 -->
		<goalRemove>
			<resources>
				<resource>
					<baseDirectory>${basedir}/src/main/webapp/WEB-INF/lib/</baseDirectory>
					<filePatterns>
						<filePattern>*.jar</filePattern>
					</filePatterns>
				</resource>
			</resources>
		</goalRemove>
		<!-- goal为errorCodeGenerator的操作配置 -->
		<goalErrorCodeGenerator>
			<resourceFiles>
				<resourceFile>msg/message</resourceFile>
			</resourceFiles>
			<targetJavaClass>com.ips.core.bacs.msg.Errors</targetJavaClass>
		</goalErrorCodeGenerator>
	</configuration>
	<executions>
		<execution>
			<id>generator-errorCode</id>
			<phase>pre-clean</phase>
			<goals>
				<goal>errorCodeGenerator</goal>
			</goals>
			<configuration>
				<resourceFiles>
					<resourceFile>msg/message</resourceFile>
				</resourceFiles>
				<targetJavaClass>com.ips.core.bacs.msg.Errors</targetJavaClass>
			</configuration>
		</execution>
	</executions>
</plugin>

执行：
mvn brevy:remove dependency:copy-dependencies