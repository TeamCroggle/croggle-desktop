# Update .desktop file
mv croggle.desktop croggle.desktop-bak
sed -e "s,Icon=.*,Icon=$PWD/croggle/res/drawable-xhdpi/ic_launcher.png,g" croggle.desktop-bak > croggle.desktop
rm croggle.desktop-bak

java -cp bin:croggle/libs/*:libs/* de.croggle.Main
