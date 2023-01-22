class User {
    String handle;
    String username;
    int followers;

    User(String handle, String username, int followers) {
        this.handle = handle;
        this.username = username;
        this.followers = followers;
    }

    String toText() {
        return this.username + " @" + this.handle;
    }
}

class Tweet {
    String content;
    User author;
    int numberOfLikes;
    String tweetId;

    Tweet(String content, User author, int numberOfLikes, String tweetId) {
        this.content = content;
        this.author = author;
        this.numberOfLikes = numberOfLikes;
        this.tweetId = tweetId;
    }

    boolean longerThan(Tweet other) {
        return this.content.length() > other.content.length();
    }

    boolean moreLikes(Tweet other) {
        return this.numberOfLikes > other.numberOfLikes;
    }

    String toText() {
        return this.author.toText() + " : " + this.content + " : " + this.numberOfLikes + " Likes";
    }

    String toLink() {
        return "https://twitter.com/" + this.author.handle + "/status/" + this.tweetId;
    }
}

class ExampleTweets {
    User user1 = new User("neo_lky", "neo", 37);
    User user2 = new User("himetsai", "Ray Tsai", 4);
    User user3 = new User("paulg", "Paul Graham", (16*100000));

    // https://twitter.com/neo_lky/status/1615903156390748160, date of posting
    Tweet tweet1 = new Tweet("I play chess", user1, 2, "1615903156390748160");
    // https://twitter.com/neo_lky/status/1615121668585701378, number of comments
    Tweet tweet2 = new Tweet("I wanna play poker", user1, 1, "1615121668585701378");
    // https://twitter.com/himetsai/status/1615666549272248328, number of views
    Tweet tweet3 = new Tweet("i can play with you", user2, 0, "1615666549272248328");
    // https://twitter.com/paulg/status/1617171287830593538, number of retweets
    Tweet tweet4 = new Tweet("My 13 yo son now uses both Twitch and Reddit. My credibility is gradually creeping upward.", user3, 884, "1617171287830593538");

    String toText1 = this.user1.toText();
    String toText2 = this.user2.toText();

    boolean longerThan1 = this.tweet1.longerThan(this.tweet2);
    boolean longerThan2 = this.tweet2.longerThan(this.tweet3);

    boolean moreLikes1 = this.tweet1.moreLikes(this.tweet2);
    boolean moreLikes2 = this.tweet2.moreLikes(this.tweet4);

    String tweetToText1 = this.tweet1.toText();
    String tweetToText2 = this.tweet2.toText();

    String toLink1 = this.tweet1.toLink();
    String toLink2 = this.tweet2.toLink();
}