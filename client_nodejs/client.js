// Very small Apollo Client to test the subscriptions of the TerroristBeApplication
import { ApolloClient, InMemoryCache } from "@apollo/client";
import { GraphQLWsLink } from "@apollo/client/link/subscriptions";
import { createClient } from "graphql-ws";
import gql from "graphql-tag";

// Link to GraphQL
const wsLink = new GraphQLWsLink(createClient({
    url: "ws://localhost:8000/graphql", // websocket endpoint
}));

// ApolloClient
const client = new ApolloClient({
    link: wsLink,
    cache: new InMemoryCache()
});

// Subscription definition
const SUBSCRIBE_HIT = gql`
    subscription {
        terroristHitAdded {
            id
            hit_date
            hit_criteria
            hit_content
            user {
                username
            }
        }
    }
`;


// Subscription execution
client.subscribe({ query: SUBSCRIBE_HIT }).subscribe({
    next({ data }) {
        console.log("Hit received:", data);
    },
    error(err) {
        console.error("Error:", err);
    },
    complete() {
        console.log("Subscription finished");
    }
});

// NodeJs Loop waiting ...
console.log("Connected to Server ... waiting for events ...");
setInterval(() => {}, 1000);
