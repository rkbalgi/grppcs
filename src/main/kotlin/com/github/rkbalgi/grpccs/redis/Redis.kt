package com.github.rkbalgi.grpccs.redis

import io.lettuce.core.RedisClient


object Redis {

    fun getRedisClient(): RedisClient {
        return RedisClient.create("redis://localhost:6379/0")
    }
}

fun getAllKeys(): List<String> {

    val conn = Redis.getRedisClient().connect()
    return conn.sync().keys("*").toList()

}