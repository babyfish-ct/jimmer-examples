#!/bin/sh

root_dir=$PWD
dirs=(\
    "java/jimmer-core" "java/jimmer-simple" "java/jimmer-sql" \
    "java/jimmer-sql-graphql" "java/jimmer-cloud" "java/save-command" \
    "kotlin/jimmer-core-kt" "kotlin/jimmer-simple-kt" "kotlin/jimmer-sql-kt" 
    "kotlin/jimmer-sql-graphql-kt" "kotlin/jimmer-cloud-kt" "kotlin/save-command-kt"\
)
for dir in "${dirs[@]}"; do
    echo --------
    echo $dir
    echo --------
    cd "$root_dir/$dir"
    if [[ $dir == java/* ]]; then
        echo "maven..."
        ./mvnw clean; ./mvnw install	
    fi
    echo "gradle ..."
    ./gradlew clean; ./gradlew build
    cd "$root_dir"
done
