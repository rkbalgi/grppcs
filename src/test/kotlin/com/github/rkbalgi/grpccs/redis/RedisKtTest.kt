package com.github.rkbalgi.grpccs.redis

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable


class RedisKtTest {

    @Test
    fun `test get all keys from Redis `() {
        val client = Redis.getRedisClient()
        Assertions.assertNotNull(client)
        val allKeys = client.connect().sync().keys("*")
        Assertions.assertAll(Executable {
            Assertions.assertTrue(allKeys.size > 0)
        })
    }


}
