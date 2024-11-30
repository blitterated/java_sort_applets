#! /bin/sh

# https://simpleit.rocks/linux/how-to-download-a-website-with-wget-the-right-way/

LOG="download.log"

IA_URL="https://web.archive.org/web/20100902050848/http://www.cs.ubc.ca/~harrison/Java"

# wget downloads to the relative directory structure on the server.
# All we need to do is remove the http(s) protocol to get the path to downloaded files.
DL_LOC=$(echo $IA_URL | sed -En "s|http[s]?://||p")

get_main_site_from_ia () {
  wget --wait=2 \
       --level=inf \
       --limit-rate=100K \
       --recursive \
       --page-requisites \
       --user-agent=Mozilla \
       --no-parent \
       --convert-links \
       --adjust-extension \
       --no-clobber \
       -e robots=off \
       "${IA_URL}\index.html" | \
  tee "${LOG}"
}

get_additional_stuff_from_ia () {
  # Grab stand ins for missing images
  curl -OL "https://web.archive.org/web/20030105162440/http://java.sun.com/people/jag/JagParody.gif" | tee -a "${LOG}"
  curl -OL "https://web.archive.org/web/19970731082706im_/http://www.cs.ubc.ca/spider/harrison/Pictures/me.jpg" | tee -a "${LOG}"
  
  # Download compiled applet class files
  for java in "${DL_LOC}"/*.java; do
    class=$(basename "${java}" | sed "s/\.java/\.class/")
    curl -OL "${IA_URL}/${class}" | tee -a "${LOG}"
  done
}

get_main_site_from_ia
get_additional_stuff_from_ia