# YouStore

YouStore is a project developed for a Java class, focusing on file conversion and cloud integration. Inspired by [Infinite-Storage-Glitch](https://github.com/DvorakDwarf/Infinite-Storage-Glitch), YouStore allows users to convert text files into video files and upload them to YouTube. Users can later download the video, which will be converted back to the original text file with minimal loss.

## Overview

The project utilizes the Spring framework to create a website where users can upload text files for conversion. The converted video files are then uploaded to YouTube for storage and sharing. Upon downloading the video, the system reverses the conversion process to retrieve the original text file.

## Note

This project was developed for educational purposes as part of a Java class project. While it demonstrates concepts related to file conversion and cloud integration, it is not intended for real-world use.

## Getting Started

To run the YouStore project locally, follow these steps:

1. **Obtaining Credentials**:
    - **YouTube API Key**: Obtain a YouTube API key by creating a project on the Google Cloud Platform (GCP) and enabling the YouTube Data API. Once enabled, generate an API key and configure it in the project settings.
    - **client_secret.json**: This file is required for OAuth 2.0 authentication with Google services. Obtain it by creating OAuth 2.0 credentials in the GCP Console. Download the JSON file containing your client secret and place it in the appropriate directory of the project. Ensure that you securely manage these credentials and do not expose them publicly.

2. Clone this repository.

3. Set up a YouTube API key and configure it in the project settings.

4. Install any required dependencies.

5. Run the project using your preferred Java development environment.

## License

This project is licensed under the [MIT License](LICENSE), allowing for free use, modification, and distribution according to the terms specified.

