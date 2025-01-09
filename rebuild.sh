#!/bin/bash

echo ----java/jimmer-core----
cd java/jimmer-core
./mvnw clean; ./mvnw install
./gradlew clean; ./gradlew build
cd -

echo ----java/jimmer-sql----
cd java/jimmer-sql
./mvnw clean; ./mvnw install
./gradlew clean; ./gradlew build
cd -

echo ----java/jimmer-sql-graphql----
cd java/jimmer-sql-graphql
./mvnw clean; ./mvnw install
./gradlew clean; ./gradlew build
cd -

echo ----java/jimmmer-cloud----
cd java/jimmer-cloud
./mvnw clean; ./mvnw install
./gradlew clean; ./gradlew build
cd -

echo ----java/save-command----
cd java/save-command
./mvnw clean; ./mvnw install
./gradlew clean; ./gradlew build
cd -

echo ----kotlin/jimmer-core-kt----
cd kotlin/jimmer-core-kt
./gradlew clean; ./gradlew build
cd -

echo ----kotlin/jimmer-sql-kt----
cd kotlin/jimmer-sql-kt
./gradlew clean; ./gradlew build
cd -

echo ----kotlin/jimmer-sql-graphql-kt----
cd kotlin/jimmer-sql-graphql-kt
./gradlew clean; ./gradlew build
cd -

echo ----kotlin/jimmer-cloud-kt----
cd kotlin/jimmer-cloud-kt
./gradlew clean; ./gradlew build
cd -

echo ----kotlin/save-command-kt----
cd kotlin/save-command-kt
./gradlew clean; ./gradlew build
cd -
