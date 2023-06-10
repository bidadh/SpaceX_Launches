package com.ideabacker.spacexlaunches

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform