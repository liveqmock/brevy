1. 资源移除插件
配置：
<plugin>
	<groupId>com.brevy</groupId>
	<artifactId>brevy-fw-plugin</artifactId>
	<version>${project.version}</version>
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
	</configuration>
</plugin>

执行：
mvn brevy-fw:remove