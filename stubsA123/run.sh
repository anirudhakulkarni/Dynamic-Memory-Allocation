#!/bin/bash
if [ $# -eq 0 ]; then
    echo "Provide input on console"
    java Driver
    pause
fi
if [ $# -eq 1 ]; then
    java Driver < "${1}"
    pause
fi
if [ $# -eq 2 ]; then
    java Driver < "${1}" > "${2}"
    pause
fi
