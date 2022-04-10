package com.mestory.test.domain.enums

enum class Queries {
    CAR , AIR, KEY;

    companion object {
        fun getRandom(): Queries {
            return when ((0..2).random()) {
                0 -> CAR
                1 -> AIR
                2 -> KEY
                else -> {
                    CAR
                }
            }
        }
    }
}