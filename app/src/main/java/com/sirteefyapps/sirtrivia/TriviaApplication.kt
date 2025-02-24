package com.sirteefyapps.sirtrivia

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TriviaApplication: Application() {
}

//
// The  TriviaApplication  class is annotated with  @HiltAndroidApp  to enable Hilt in the application.
// The  @HiltAndroidApp  annotation triggers Hilt’s code generation, which generates the necessary components and modules for the application.
// The  TriviaApplication  class extends  Application  and is the entry point for the application.
// Step 3: Add the dependencies
// Open the  build.gradle  file for the app module and add the following dependencies: