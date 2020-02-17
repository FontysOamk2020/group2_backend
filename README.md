# Hotspotted-server

## Running this local
In order to run this project local you need to configure a few parameters in the `Run/Debug Configuration`.

| Name | Value |
| ---:| --- |
| `SPRING_DATABASE_URL` | jdbc:postgresql://xxxxxxxxxxxxxxx |
| `SPRING_DATABASE_USERNAME` | xxxxxxxxxxxxxxx |
| `SPRING_DATABASE_PASSWORD` | xxxxxxxxxxxxxxx |
| `OATH2_IDENTIFIER` | xxxxxxxxxxxxxxx |
| `OATH2_DOMAIN` | xxxxxxxxxxxxxxx |

## Production host
This project is hosted on Heroku. Here is the base url of the project: https://hotspotted-server.herokuapp.com/.

Heroku offers a free tier. I use this tier for the hosting. The downside of this is dat when the server has not been used for over 30 minutes, the server puts itself in 'sleep' mode. This means the first request will take between 10/20s longer. After this the server functions normally.

## CI/CD
Every pull request that targets the master branch will automatically be tested with GitHub Actions. You can view the status of the CI in the pull request itself. Before you can merge this status check will need to be passed. Also, another person needs to approve the pull request by reviewing it.

After successfully merging the pull request GitHub automatically deploys the app to Heroku.