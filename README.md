# Music Reviews

A COMP6000 course project for browsing album reviews. Signed-in users can read
reviews, and administrators can publish them.

## Run locally

1. Create a MySQL database named `mydb` and import
   `web-app-semester-project-main/reviews.sql` into it. The script initializes
   a new database. It does not migrate existing tables.
2. Set `MUSICREVIEWS_DB_URL` (for example, `jdbc:mysql://localhost:3306/mydb`),
   `MUSICREVIEWS_DB_USER`, and `MUSICREVIEWS_DB_PASSWORD` in the environment
   where Tomcat runs. The application defaults to the local URL above and
   the `root` user with an empty password.
3. Run `mvn clean package` in `web-app-semester-project-main`. Deploy the
   resulting WAR in `target/` to Tomcat 9 and open `login.jsp` under the
   deployed application path.
4. Sign up for a normal account. To test publishing in your development
   database, set that account's `users.privilege` to `1` and sign in again.
