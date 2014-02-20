#!/bin/bash
cd "$(dirname "$0")"
java -cp bin:croggle/libs/*:libs/* de.croggle.Main
