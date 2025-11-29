# Multi Counter App

A simple Jetpack Compose app that displays a scrollable list of independent counters. Each counter can increase or decrease without affecting others.

## Features

* Scrollable list of counters using **LazyColumn**
* Each counter has a name (Counter_#), plus “+” and “–” buttons
* All counters update **independently**
* Optional features implemented:

  * Add a new counter
  * Remove an existing counter
* Uses **ViewModel + mutable state** for proper state management
* Material 3 design with light/dark theme

## Tech Used

* Kotlin
* Jetpack Compose
* Material 3
* ViewModel / State
* LazyColumn

## Project Structure

```
com.multiCounterApp/
 ├── MainActivity.kt
 ├── ui/
 │    ├── MultiCounterViewModel.kt
 │    ├── CounterItem.kt
 │    └── Theme.kt
 ├── data/
 │    └── Counter.kt
 └── README.md
```

## How to Run

1. Clone the repo:

   ```
   git clone https://github.com/YOUR_USERNAME/multiCounterApp.git
   ```
2. Open in **Android Studio**
3. Press **Run ▶**
4. Choose emulator or device

## Requirements

* Android Studio Hedgehog or newer
* Kotlin 1.9.10 (required for Compose Compiler 1.5.3)
* Compose BOM 2024+

## Testing

Includes 1 unit test for counter logic.

## Screenshots

<img width="1908" height="956" alt="image" src="https://github.com/user-attachments/assets/683e5dd0-de1e-42db-a1e1-6e4c80a9d8d5" />


## License

Academic use only.

---

If you need a **README with screenshot links**, **APK section**, or a **GitHub release template**, I can generate it too!
