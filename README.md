# movie-details-service

This microservice handles movie information.

1) An initial migration : The data available in an sheet is read and stored in elasticsearch in preferred schema, for an optimised search implementation.

2) A server : The server exposes REST API's to search the movie database based on preferred languages, favourite actors and directors.
