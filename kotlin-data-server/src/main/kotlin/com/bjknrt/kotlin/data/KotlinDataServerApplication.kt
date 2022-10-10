package com.bjknrt.kotlin.data

import me.danwi.kato.client.ImportKatoClients
import me.danwi.kato.server.EnableKatoServer
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@ImportKatoClients
@EnableKatoServer
@SpringBootApplication(scanBasePackages = ["com.bjknrt.kotlin.data","com.bjknrt.framework"])
class KotlinDataServerApplication

fun main(args: Array<String>) {
    runApplication<KotlinDataServerApplication>(*args)
}