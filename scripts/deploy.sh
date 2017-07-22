#!/bin/bash
mkdir out
scripts/build.sh
cp -r resources/public/* out/
git checkout gh-pages
git pull
cp -r out/* .
rm -rf out
git add .
git commit
git push origin gh-pages
git checkout master
