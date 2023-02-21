import tester.*;


interface Tweet {
    public boolean isStartOfThreadBy(String author);
    public int totalLikes();
    public String unrollThread();
}

class TextTweet implements Tweet {
    String contents;
    int likes;
    String author;

    TextTweet (String contens, int likes, String author) {
        this.contents = contens;
        this.likes = likes;
        this.author = author;
    }

    public boolean isStartOfThreadBy(String author) {
        return author.equals(this.author);
    }

    public int totalLikes() {
        return this.likes;
    }

    public String unrollThread() {
       return this.author + "\n" + 
                this.likes + " likes\n" + 
                this.contents + "\n";
    }
}

class ReplyTweet implements Tweet {
    String contents;
    int likes;
    String author;
    Tweet replyTo;

    ReplyTweet (String contens, int likes, String author, Tweet replyTo) {
        this.contents = contens;
        this.likes = likes;
        this.author = author;
        this.replyTo = replyTo;
    }
    
    public boolean isStartOfThreadBy(String author) {
        return author.equals(this.author) &&
                this.replyTo.isStartOfThreadBy(author);
    }

    public int totalLikes() {
        return this.likes + this.replyTo.totalLikes();
    }

    public String unrollThread() {
        return this.replyTo.unrollThread() +
                this.author + "\n" + 
                this.likes + " likes\n" + 
                this.contents + "\n";
     }
}

class Tweets {
    TextTweet tt1 = new TextTweet("gm", 2, "neo");
    TextTweet tt2 = new TextTweet("hi", 10, "random");
    ReplyTweet rt1 = new ReplyTweet("good morning", 5, "neo", tt1);
    ReplyTweet rt2 = new ReplyTweet("hello", 0, "neo", tt2);

    boolean testTextTweetIsStartOfThreadBy(Tester t) {
        return t.checkExpect(this.tt1.isStartOfThreadBy("neo"), true) && 
                t.checkExpect(this.tt1.isStartOfThreadBy("random"), false);
    }

    boolean testTextTotalLikes(Tester t) {
        return t.checkExpect(this.tt1.totalLikes(), this.tt1.likes) && 
                t.checkExpect(this.tt2.totalLikes(), 10);
    }

    boolean testTextTweetUnrollThread(Tester t) {
        return t.checkExpect(this.tt1.unrollThread(), "neo\n2 likes\ngm\n") && 
                t.checkExpect(this.tt2.unrollThread(), "random\n10 likes\nhi\n");
    }

    boolean testReplyTweetIsStartOfThreadBy(Tester t) {
        return t.checkExpect(this.rt1.isStartOfThreadBy("neo"), true) && 
                t.checkExpect(this.rt2.isStartOfThreadBy("random"), false);
    }
    boolean testReplyTweetTotalLikes(Tester t) {
        return t.checkExpect(this.rt1.totalLikes(), 7) && 
                t.checkExpect(this.rt2.totalLikes(), 10);
    }
    boolean testReplyTweetUnrollThread(Tester t) {
        return t.checkExpect(this.rt1.unrollThread(), this.rt1.replyTo.unrollThread() + "neo\n5 likes\ngood morning\n") && 
                t.checkExpect(this.rt2.unrollThread(), "random\n10 likes\nhi\nneo\n0 likes\nhello\n");
    }
}