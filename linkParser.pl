#!/usr/bin/perl
# Jeoff Villanueva
# CSCE 343
# 2 October 2015
# Homework 2
# Resources:
# perl - http://search.cpan.org/dist/Perl-Tutorial/lib/Perl/Tutorial/HelloWorld.pod
# get - http://perlmeme.org/tutorials/lwp.html
# split - http://perlmeme.org/howtos/perlfunc/split_function.html
# regex - http://stackoverflow.com/questions/7220716/perl-regular-expression-to-match-everything-between-double-quotes
# compare strings - http://stackoverflow.com/questions/1175390/how-do-i-compare-two-strings-in-perl
# file - http://perlmaven.com/writing-to-files-with-perl
# arguments - http://alvinalexander.com/perl/perl-command-line-arguments-read-args
# arguments empty- http://www.perlmonks.org/?node_id=41177
#use strict;
#use warnings;
if(@ARGV == 1){
  use LWP::Simple;
  my $site = get($ARGV[0]) or die 'Unable to get page';
  my @lines = split('\n', $site);
  my $lineNumber = 0;
  my $result;
  my $i = 1;
  my $type;
  open(my $fh, '>', 'perlLinks.txt');
  foreach my $lin (@lines){
    my $temp = $lin;
    if ($temp =~ /a href="(.+?)"/ || $temp =~ /a target="_blank" href="(.+?)"/) {
      $type = 'relative';
      $result = $1;
      if($result ne "#"){
        if ($result =~ /http:/){
          $type = 'absolute';
        }
        if ($result =~ /https:/){
          $type = 'absolute';
        }
        if ($result =~ /ftp:/){
          $type = 'absolute';
        }
        print $fh "$i $result is $type\n";
      }
    }
    $i++;
  }
  close $fh;
}
else{
  print "Usage: fileName.pl [www.siteAddress.com]\n"
}
