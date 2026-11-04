package org.prepare;

import java.io.IOException;

public class UsersPrepareApi extends AbsRestClient {

    public void registerUser(User user) throws IOException, InterruptedException {
        post("/api/auth/register",
                String.format("\n{username: \"%s\", email: \"%s\", password: \"%s\"}",
                        user.getUserName(), user.getEmail(), user.getPassword()));
    }




}
