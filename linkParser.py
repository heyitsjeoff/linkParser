#Jeoff Villanueva
#CSCE 343
#5 OCtober 2015
#homework 2
#Resources:
# page source - http://stackoverflow.com/questions/3533528/python-web-crawlers-and-getting-html-source-code
# file - http://stackoverflow.com/questions/6159900/correct-way-to-write-line-to-file-in-python
# length of array - http://stackoverflow.com/questions/518021/getting-the-length-of-an-array-in-python
# ++ - http://stackoverflow.com/questions/2632677/python-integer-incrementing-with
# read line page source - http://stackoverflow.com/questions/10019728/search-html-line-by-line-with-regex-in-python
# add number to string - http://stackoverflow.com/questions/2847386/python-string-and-integer-concatenation
# dont use regex suggestion - http://stackoverflow.com/questions/849912/python-regex-how-to-find-a-string-between-two-sets-of-strings
# install BeautfilSoup - http://www.crummy.com/software/BeautifulSoup/bs4/doc/
# beautifulsoup - http://stackoverflow.com/questions/4462061/beautiful-soup-to-parse-url-to-get-another-urls-data
# import error - http://stackoverflow.com/questions/5663980/importerror-no-module-named-beautifulsoup
# splitline - http://stackoverflow.com/questions/15422144/how-to-read-a-long-multiline-string-line-by-line-in-python
# substring - http://stackoverflow.com/questions/5473014/test-a-string-for-a-substring
# arguments - http://www.tutorialspoint.com/python/python_command_line_arguments.htm
# argument parse - https://docs.python.org/2/howto/argparse.html
from bs4 import BeautifulSoup
import urllib2
import string
import re
import sys
import argparse
if len(sys.argv) != 2:
    print "Usage: fileName.py [http://www.siteName.com]"
else:
    parser = argparse.ArgumentParser()
    parser.add_argument("site", help="site is the address of the web site [http://www.siteName.com]")
    args = parser.parse_args()
    response = urllib2.urlopen(args.site)
    page_source = response.read()
    j = 1
    f = open('pythonLinks.txt','w')
    for line in page_source.splitlines():
        soup = BeautifulSoup(line, "html.parser")
        soup.prettify()
        lType = " is relative"
        for anchor in soup.findAll('a', href=True):
            if anchor['href'] != '#':
                temp = anchor['href']
                if "http:" in temp:
                    lType = " is absolute"
                if "https:" in temp:
                    lType = " is absolute"
                if "ftp:" in temp:
                    lType = " is absolute"
                f.write(`j` + ' ' + anchor['href'] + lType + '\n')
        j+=1
    f.close()

