cd /home/master/programacao/projetos/pfs-v2.0/source
echo "compilando"

javac *.java
cd ..
rm class-files/*.class
mv source/*.class class-files
cd class-files
java Main

