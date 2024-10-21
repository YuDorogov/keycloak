ARG VERSION=0.5.0
ARG KEYCLOAK_VERSION=26.0.0

# Build extensions.
FROM maven:3-openjdk-17 as builder
COPY extensions /src/extensions
RUN cd /src/extensions && mvn package

# Configure Keycloak.
FROM quay.io/keycloak/keycloak:${KEYCLOAK_VERSION} as keycloak
ARG VERSION
ARG KEYCLOAK_VERSION
COPY --from=builder \
     /src/extensions/target/keycloak-invaders-${VERSION}.jar \
     /opt/keycloak/providers/
COPY themes/invaders/ /opt/keycloak/themes/invaders/
RUN /opt/keycloak/bin/kc.sh build \
    --db=postgres \
    --metrics-enabled=true \
    --features=scripts

# Build Keycloak.
FROM quay.io/keycloak/keycloak:${KEYCLOAK_VERSION}
ARG VERSION
ARG KEYCLOAK_VERSION
COPY --from=keycloak /opt/keycloak/lib/quarkus/ /opt/keycloak/lib/quarkus/
COPY --from=keycloak /opt/keycloak/providers/ /opt/keycloak/providers/
COPY --from=keycloak /opt/keycloak/themes/ /opt/keycloak/themes/
WORKDIR /opt/keycloak
ENTRYPOINT ["/opt/keycloak/bin/kc.sh"]
