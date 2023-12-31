//
//  ViewModel.swift
//  iosApp
//
//  Created by Arthur Kazemi on 11/6/2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension ContentView {
  enum LoadableLaunches {
    case loading
    case result([RocketLaunch])
    case error(String)
  }
  
  @MainActor
  class ViewModel: ObservableObject {
    let sdk: SpaceXSDK
    @Published var launches = LoadableLaunches.loading
    
    init(sdk: SpaceXSDK) {
      self.sdk = sdk
      self.loadLaunches(forceReload: false)
    }
    
    func loadLaunches(forceReload: Bool) {
      Task {
        do {
          self.launches = .loading
          let launches = try await sdk.getLaunches(forceReload: forceReload)
          self.launches = .result(launches)
        } catch {
          self.launches = .error(error.localizedDescription)
        }
      }
    }
  }
}
