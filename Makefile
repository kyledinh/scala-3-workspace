export SHELL := /bin/bash

## MAIN ##################
.PHONY: all check compile fmt test

all:
	$(MAKE) fmt 
	$(MAKE) compile
	$(MAKE) test

check:
	@echo "Checks...."

compile:
	@sbtn clean
	@sbtn compile

fmt:
	@scalafmt

test:
	@sbtn test