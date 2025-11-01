#!/bin/sh
set -eu

ARGS="--appendonly yes"
if [ -n "${REDIS_PASSWORD:-}" ]; then
  ARGS="$ARGS --requirepass ${REDIS_PASSWORD}"
fi

exec redis-server $ARGS