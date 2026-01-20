DESCRIPTION = "hailo firmware \
				hailo8 chip firmware (hailo_fw.bin) \
				the recipe copies the file to /lib/firmware/hailo/ on the target device's root file system"

BASE_URI = "https://hailo-hailort.s3.eu-west-2.amazonaws.com"
FW_AWS_DIR = "Hailo8/${PV}/FW"
FW = "hailo8_fw.${PV}.bin"
LICENSE_FILE = "LICENSE"
SRC_URI = "${BASE_URI}/${FW_AWS_DIR}/${FW};md5sum=a9a80585a6733674e2ca158ec15971c4 \
		${BASE_URI}/${FW_AWS_DIR}/${LICENSE_FILE};md5sum=263ee034adc02556d59ab1ebdaea2cda"

LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

FW_PATH = "${WORKDIR}/hailo8_fw.${PV}.bin"

# Firmware must be in /lib/firmware for kernel request_firmware() to find it
# Using base_libdir (/lib) instead of nonarch_base_libdir (/usr/lib)
FIRMWARE_INSTALL_DIR = "${base_libdir}/firmware/hailo"

do_install() {
	# Install firmware to /lib/firmware/hailo (required path for kernel request_firmware)
	install -d ${D}${FIRMWARE_INSTALL_DIR}
	install -m 0644 ${FW_PATH} ${D}${FIRMWARE_INSTALL_DIR}/hailo8_fw.bin
}

# Package contents - firmware in /lib/firmware/hailo
FILES:${PN} += "${FIRMWARE_INSTALL_DIR}/hailo8_fw.bin"
