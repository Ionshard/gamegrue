#!/bin/bash
rm -rf out
mkdir out
scripts/build.sh
cp -r resources/public/* out/
git checkout gh-pages
git pull
cp -r out/* .
git add .
git commit
git push origin gh-pages
git checkout master
