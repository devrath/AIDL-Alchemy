package com.istudio.server

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import java.security.SecureRandom
import java.util.Random

class AppServerService : Service() {
    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        return binder
    }

    private val binder: ServerInterface.Stub = object : ServerInterface.Stub() {
        override fun getIntegerData(): Int {
            val randomValue = Random().nextInt()
            Log.d(TAG, randomValue.toString())
            return randomValue
        }

        override fun getLongData(): Long {
            val randomValue = Random().nextLong()
            Log.d(TAG, randomValue.toString())
            return randomValue
        }

        override fun getCharacterData(): Char {
            val random = Random()
            // Generating a random char between 'a' and 'z' (inclusive)
            return (random.nextInt(26) + 'a'.code).toChar()
        }

        override fun getFloatData(): Float {
            val randomValue = Random().nextFloat()
            Log.d(TAG, randomValue.toString())
            return randomValue
        }

        override fun getDoubleData(): Double {
            val randomValue = Random().nextDouble()
            Log.d(TAG, randomValue.toString())
            return randomValue
        }

        override fun getStringData(): String {
            val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
            val random = SecureRandom()
            val length = 10
            val sb = StringBuilder(length)
            for (i in 0 until length) {
                val randomIndex = random.nextInt(characters.length)
                val randomChar = characters[randomIndex]
                sb.append(randomChar)
            }
            return sb.toString()
        }
    }

    companion object {
        private const val TAG = "AppServerService"
    }
}