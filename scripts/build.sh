#!/bin/bash

echo "Building with APIKEY $APIKEY"
lein clean
lein cljsbuild once min
