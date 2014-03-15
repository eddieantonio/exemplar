#!/bin/sh

# Downloads some dependencies that are not present in the Maven Central
# repositories.

STANFORD_TREGEX=stanford-tregex-2.0.5.jar.zip


mkdir -p lib
cd lib 

xargs -n 1 curl -O <<EOF
http://search.maven.org/remotecontent?filepath=edu/stanford/nlp/stanford-corenlp/1.3.3/stanford-corenlp-1.3.3-models.jar
http://www.java2s.com/Code/JarDownload/stanford/${STANFORD_TREGEX}
http://www.maltparser.org/mco/english_parser/engmalt.linear-1.7.mco
EOF

# Isn't... isn't a JAR just a zip with a manifest file?
# Regardless, the stanford-tregex needs to be unzipped...
unzip $STANFORD_TREGEX

# Also, we can just go ahead and delete the original zip.
rm $STANFORD_TREGEX
