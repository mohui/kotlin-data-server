package com.bjknrt.kotlin.data

import com.zaxxer.hikari.HikariDataSource
import org.springframework.context.annotation.Bean
import javax.sql.DataSource

class ConfigTest {

    @Bean
    fun dataSourceOne(): DataSource {
        val dataSource = HikariDataSource()
        dataSource.driverClassName = AbstractContainerBaseTest.MY_SQL_CONTAINER.driverClassName
        dataSource.username = AbstractContainerBaseTest.MY_SQL_CONTAINER.username
        dataSource.password = AbstractContainerBaseTest.MY_SQL_CONTAINER.password
        dataSource.jdbcUrl = AbstractContainerBaseTest.MY_SQL_CONTAINER.jdbcUrl
        return dataSource
    }

}