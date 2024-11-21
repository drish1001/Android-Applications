# Android-Applications

This repository contains multiple Android projects showcasing various functionalities and use cases. Each project is a standalone application designed to demonstrate specific features or concepts in Android development.

## Projects in the Repository

#### 1. **Mail Sender**  
A straightforward Android app that enables users to send emails programmatically using the JavaMail API.  
- **Key Features**: Simple UI for composing emails, sends emails via JavaMail API.  
- **Directory**: `Mail_Sender`


#### 2. **Phone Call**  
An Android app that demonstrates how to initiate phone calls directly from within the application, using built-in dialer functionality.  
- **Key Features**: Dialer functionality, permission handling for phone calls.  
- **Directory**: `PhoneCall`


#### 3. **SMS Sender**  
A simple Android app that allows users to send SMS messages programmatically, providing an easy-to-use interface for composing and sending text messages.  
- **Key Features**: Easy-to-use interface for composing SMS, handles SMS permissions.  
- **Directory**: `SMS_Sender`


#### 4. **SMS Sender via API**  
An enhanced version of the SMS Sender app, integrating with an external API to send SMS messages with better control over delivery and error handling.  
- **Key Features**: API integration for SMS sending, error handling and response management.  
- **Directory**: `SMS_Sender_via_API`


#### 5. **URL Viewer**  
A lightweight app that allows users to view web content by entering a URL, leveraging WebView for seamless browsing within the app.  
- **Key Features**: WebView integration, basic navigation controls (back, forward).  
- **Directory**: `URL_Viewer`

## Common Features Across Projects

- Each project includes:
  - A dedicated `MainActivity.java` file handling the core logic.
  - XML layouts designed for responsive and user-friendly interfaces.
  - Gradle build files (`build.gradle`) with dependencies specific to each project.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/drish1001/Android-Applications.git
   ```
2. Navigate to the desired project directory (e.g., `Mail_Sender` or `PhoneCall`).
3. Open the project in Android Studio.
4. Sync Gradle files to download dependencies.
5. Build and run the app on an Android device or emulator.

## Dependencies

Each project uses standard Android dependencies, such as:

- `androidx.appcompat` for backward compatibility.
- `androidx.constraintlayout` for flexible UI design.
- Testing libraries like JUnit and Espresso.

Dependencies are specified in the respective `build.gradle` files of each project.

## Permissions

Some projects require specific permissions, which are declared in their respective `AndroidManifest.xml` files. Examples include:

- Internet access (`android.permission.INTERNET`) for Mail Sender and URL Viewer apps.
- Phone call permission (`android.permission.CALL_PHONE`) for the Phone Call app.
- SMS permissions (`android.permission.SEND_SMS`) for SMS-related apps.

Ensure these permissions are granted at runtime if targeting Android 6.0 (API level 23) or higher.

## Contributing

Contributions are welcome! If you have ideas for new projects or improvements to existing ones:

1. Fork this repository.
2. Create a new branch (`feature/new-feature`).
3. Commit your changes and push them to your forked repository.
4. Submit a pull request with a detailed description of your changes.
