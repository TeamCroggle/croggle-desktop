#!/bin/bash
cd ..
zip -r croggle-desktop/croggle\
	croggle-desktop/bin\
	croggle-desktop/croggle.desktop\
	croggle-desktop/launch.bat\
	croggle-desktop/launch.command\
	croggle-desktop/launch.sh\
	croggle-desktop/libs/gdx-backend-lwjgl.jar\
	croggle-desktop/libs/gdx-backend-lwjgl-natives.jar\
	croggle-desktop/libs/gdx-freetype-natives.jar\
	croggle-desktop/libs/gdx-natives.jar\
	croggle-desktop/libs/sqlite-jdbc-3.7.15-M1.jar\
	croggle-desktop/croggle/assets\
	croggle-desktop/croggle/res\
	croggle-desktop/croggle/libs/gdx.jar\
	croggle-desktop/croggle/libs/gdx-freetype.jar
