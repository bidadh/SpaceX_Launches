package com.ideabacker.spacexlaunches.shared

import com.ideabacker.spacexlaunches.shared.cache.Database
import com.ideabacker.spacexlaunches.shared.cache.DatabaseDriverFactory
import com.ideabacker.spacexlaunches.shared.entity.RocketLaunch
import com.ideabacker.spacexlaunches.shared.network.SpaceXApi

class SpaceXSDK (databaseDriverFactory: DatabaseDriverFactory) {
  private val database = Database(databaseDriverFactory)
  private val api = SpaceXApi()

  @Throws(Exception::class)
  suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
    val cachedLaunches = database.getAllLaunches()
    return if (cachedLaunches.isNotEmpty() && !forceReload) {
      cachedLaunches
    } else {
      api.getAllLaunches().also {
        database.clearDatabase()
        database.createLaunches(it)
      }
    }
  }
}
