# FFmpeg Binary Setup for Linux

---

## Step 1: Go to Official Website
- Visit: https://www.ffmpeg.org/
- Clone the repository or download the source archive.

## Step 2: Download Source Code
- Download the latest release from:
  - **Download → Source Code**

## Step 3: Install Build Dependencies

### Debian / Ubuntu (APT)
```bash
sudo apt update
sudo apt install build-essential yasm cmake libtool unzip wget
```

### Fedora (DNF)
```bash
sudo dnf install gcc gcc-c++ make yasm cmake libtool unzip wget
```

### Arch Linux (Pacman)
```bash
sudo pacman -S base-devel yasm cmake libtool unzip wget
```

## Step 4: Build FFmpeg
```bash
cd /path/to/source
./configure
make -j$(nproc)
```

## Step 5: Rename & Store
- Locate the built binary **ffmpeg**
- Rename it exactly:
**ffmpeg**
- Move it to **src/resources/ffmpeg/linux**
