### Dependencies for Navigation

#### In build.gradle file add the below dependencies
```groovy
dependencies {
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("androidx.navigation:navigation-fragment:2.7.0")
    implementation("androidx.navigation:navigation-ui:2.7.0")
    implementation("androidx.navigation:navigation-dynamic-features-fragment:2.7.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.0")
}
```