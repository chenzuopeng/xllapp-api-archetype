archetypeDir="`pwd`"
cd $archetypeDir/../
projectDir="`pwd`"
srcDir=$projectDir/src
archetypeSrcDir=$archetypeDir/src/main/resources/archetype-resources/src
ln -v -s $srcDir $archetypeSrcDir
cd $archetypeDir
mvn clean deploy
rm $archetypeSrcDir