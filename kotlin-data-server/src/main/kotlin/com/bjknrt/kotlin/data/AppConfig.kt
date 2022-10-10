package com.bjknrt.kotlin.data

import feign.Logger
import com.bjknrt.kotlin.data.Repository
import me.danwi.sqlex.core.DaoFactory
import me.danwi.sqlex.spring.ImportSqlEx
import me.danwi.sqlex.spring.SpringDaoFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
@ImportSqlEx(Repository::class) // sqlex
class AppConfig(val dataSource: DataSource) {

    /**
     * Sqlex数据源工厂
     */
    @Bean
    fun factory(): DaoFactory {
        val factory = SpringDaoFactory(dataSource, Repository::class.java)
        factory.migrate()
        factory.check()
        return factory
    }

    /**
     * Feign 日志级别
     **/
    @Bean
    fun feignLogLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}