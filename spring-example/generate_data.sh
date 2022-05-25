#!/bin/bash
if [ ! $# -eq 1 ]; then
  echo "Syntax `basename $0` <number of entries>"
  exit 1
fi

number_of_entries=$1
filename=".sql/02_data.sql"
echo "generating $number_of_entries database entries..."
rm $filename
echo "USE postdb;" >> $filename
echo "DELETE FROM posts;" >> $filename
for((i=1; i <= number_of_entries;i++))
do
  echo "INSERT INTO posts (title,content) VALUES ('post$i', 'This is post$i');" >> $filename
done
fi
echo "...done"
exit 0
