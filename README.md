# Spring OAuth2 Cloud Services BFF Pattern Demo

This repo contains demo Spring cloud services (`authorisation-server`, `resource-server`, `resource-server2`). The services are accessible from a frontend app (here `react-client`) via a BFF/gateway (`oauth-gateway`).

The BFF acts as a proxy, token relay, and OAuth2 client, allowing access to the resource servers once users are authenticated and grant authorisation via the authorisation server. 

## Getting Started

1. Add the following entries to your hosts file (in UNIX systems `etc/hosts`):

    ```
    127.0.0.1 gateway
    127.0.0.1 auth-server
    127.0.0.1 resource-server
    127.0.0.1 resource-server2
    ```

    This will ensure that cookies aren't overwritten, and make it easier to track which service is in use.

2. Run all of the applications starting with the authorisation server.

3. Open a browser at `http://gateway:7000`. You should now see the UI from React frontend.

4. Click the 'Log in' button at the top right to log in and authorise the app to access resources from the resource servers ("articles" and your profile). 

    ```
    Username: 'Admin'
    Password: 'password'
    ```

    If you want to login using GitHub you will need to [create an OAuth2 app](https://docs.github.com/en/apps/oauth-apps/building-oauth-apps/creating-an-oauth-app) and add your client-id and client-secret as environment variables when running the authorisation server (`OAUTH2_GITHUB_ID` and `OAUTH2_GITHUB_SECRET` respectively).
