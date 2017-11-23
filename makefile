launch-payara:
	cd docker ; mkdir -p data/payara ; chmod -R o+rw data; docker-compose up -d payara-full
deploy:
	export MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1" ; mvn -T 1C clean package
	cp target/app01-1.1-ejb-SNAPSHOT.war docker/data/payara/
rm:
	cd docker ; docker-compose down
restart:
	make rm && make launch-payara
logs:
	cd docker ; docker-compose logs -f
access_db:
	cd docker ; docker-compose exec db /usr/bin/psql glassfish glassfish
