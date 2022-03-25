package com.rustem.simplecrudapplication.config

import com.zaxxer.hikari.HikariDataSource
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class JpaConfiguration {
    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Value("\${spring.datasource.url}")
    private lateinit var databaseUrl: String

    @Value("\${spring.datasource.username}")
    private lateinit var username: String

    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @Value("\${jpa.hibernate.show-sql}")
    private lateinit var showSql: String

    @Value("\${jpa.hibernate.format-sql}")
    private lateinit var formatSql: String

    @Value("\${jpa.hibernate.use_sql_comments}")
    private lateinit var useSqlComments: String

    @Value("\${jpa.hibernate.ddl-auto}")
    private lateinit var ddlAuto: String

    @Value("\${jpa.hibernate.dialect}")
    private lateinit var dialect: String

    @Value("\${jpa.hibernate.temp.use_jdbc_metadata_defaults}")
    private lateinit var useJdbcMetaDataDefaults: String

    @Value("\${liquibase.change-log}")
    private lateinit var liquibaseChangeLogLocation: String

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.rustem.simplecrudapplication.model")
        val vendorAdapter: JpaVendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaProperties(additionalProperties())
        return em
    }

    @Bean
    @Primary
    fun dataSource(): DataSource {
        return DataSourceBuilder.create()
            .type(HikariDataSource::class.java)
            .driverClassName(driverClassName)
            .url(databaseUrl)
            .username(username)
            .password(password)
            .build()
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory?): PlatformTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = emf
        return transactionManager
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor {
        return PersistenceExceptionTranslationPostProcessor()
    }

    @Bean
    fun liquibase(): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.changeLog = liquibaseChangeLogLocation
        liquibase.dataSource = dataSource()
        return liquibase
    }

    private fun additionalProperties(): Properties {
        val properties = Properties()
        properties.setProperty("hibernate.show_sql", showSql)
        properties.setProperty("hibernate.format_sql", formatSql)
        properties.setProperty("hibernate.use_sql_comments", useSqlComments)
        properties.setProperty("hibernate.hbm2ddl.auto", ddlAuto)
        properties.setProperty("hibernate.dialect", dialect)
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", useJdbcMetaDataDefaults)
        return properties
    }
}