#!/bin/sh

MIMETYPE="text/x-java";
SUBDIR="TEX-Files";
POSTFIX="-listing.tex";
LISTING_FILENAME="listings.tex";

rm $LISTING_FILENAME;
mkdir $SUBDIR;

ls | while read FILE
  do
    # check if it is a Java file
    if [ "`file -b --mime-type "$FILE"`" = $MIMETYPE ]; then
	# create file
	echo '\\newpage \section{'$FILE"} \lstinputlisting{$FILE}" > $SUBDIR/$FILE$POSTFIX;
	# add as input
	echo "\input{$SUBDIR/$FILE$POSTFIX}" >> $LISTING_FILENAME;
    fi    
  done
