<h1 align="center">
  VdoKit <img src="src/resources/icons/logo.png" width="35" height="35" />
</h1>

VdoKit is a simple Java GUI-based FFMPEG wrapper.  
It allows users to perform common video operations like **format conversion** and **caption embedding** using an easy Swing interface.

---
## 📌 Table of Contents

- [✨ Features](#-features)
- [🛠 Requirements](#-requirements)
- [📥 Installation](#-installation)
- [▶️ Run](#️-run)
- [📂 Project Structure](#-project-structure)
- [🙏 Credits](#-credits)
- [📜 License](#-license)
- [👨‍💻 Author](#-author)

---

## ✨ Features

### ✅ Video Format Conversion
- Convert videos into different formats (mp4, mkv, webm, avi, etc.)
- Simple file selection system
- Output file is automatically generated

### ✅ Caption Embedding
Supports both:

#### 🔹 Soft Embedding (Subtitle Track)
- Adds subtitle file as a subtitle track
- Subtitles can be turned ON/OFF in media players

#### 🔹 Hard Embedding (Burn Subtitles)
- Permanently burns subtitles into the video

### ✅ Video Scaling
- Optional scaling feature using FFmpeg `scale` filter
- Includes predefined resolutions (1920x1080, 1280x720, etc.)
- Scaling option can be enabled/disabled with a checkbox

### ✅ Progress Panel
- Shows progress panel while FFmpeg process is running
- Prevents UI freezing using `SwingWorker`

---

## 🛠 Requirements

- **Java 17+** (Recommended)
- **FFMPEG Binary** (Required)  
    - A supported FFMPEG executable for your operating system   

- **Cross Platform:**   
    - Works on both **Windows** and **Linux**

---
## 📥 Installation


### 1. Download FFmpeg

- Download or build FFMPEG binary from official [website](https://www.ffmpeg.org/)   

### 2. Add FFmpeg Binary to Project

VdoKit requires FFmpeg binary to be placed manually inside the project.

- #### Windows

    - Place `ffmpeg.exe` inside: `src/resources/ffmpeg/windows/`

- #### Linux
    - Place `ffmpeg` inside: `src/resources/ffmpeg/linux/`

⚠️ Important:   

- File name must be exactly `ffmpeg.exe` (Windows)
- File name must be exactly `ffmpeg` (Linux)
---

## ▶️ Run

- ### Windows
    - Run:  ```scripts/build.bat```

- ### Linux
    - Run:  ```bash
chmod +x scripts/run.sh
./scripts/run.sh ```

---
## 📂 Project Structure
    src
    ├── main
    │   └── java
    │       └── dev
    │           └── vdokit
    │               ├── core
    │               │   └── ProcessRunner.java
    │               ├── platform
    │               │   └── PlatformDetector.java
    │               ├── request
    │               │   ├── CaptionRequest.java
    │               │   └── FormatRequest.java
    │               ├── ui
    │               │   ├── CaptionPanel.java
    │               │   ├── FormatPanel.java
    │               │   ├── MainFrame.java
    │               │   ├── MainPanel.java
    │               │   └── ProgressPanel.java
    │               └── VdoKitApp.java
    └── resources
        ├── ffmpeg
        │   ├── linux
        │   │   └── binary here
        │   └── windows
        │       └── binary here
        └── icons
---

## 🙏 Credits

This project uses the **FFmpeg** multimedia framework.  
FFmpeg is developed and maintained by the FFmpeg team.  
Official website: [https://ffmpeg.org/](https://ffmpeg.org/)

---
## 📜 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
## 👨‍💻 Author
Developed by [its-mohitkumar-7](https://github.com/its-mohitkumar-7)

