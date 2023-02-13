import tester.*;

class TextTweet {
    String contents;
    int likes;

    TextTweet(String contents, int likes) {
        this.contents = contents;
        this.likes = likes;
    }

    boolean hasMention(String username) {
        return this.contents.contains("@" + username + " ") || 
                this.contents.endsWith("@" + username);
    }
    
    boolean hasLike() {
        return this.likes > 0;
    }
    
    String firstMention() {
        int atIndex = this.contents.indexOf("@");
        if (atIndex != -1) {
            String subString = this.contents.substring(atIndex + 1);
            int spaceIndex = subString.indexOf(" ");
            if (spaceIndex != -1) {
                return subString.substring(0, spaceIndex);
            }
            
            return "";
        }
        return "";
    }
}

class ReplyTweet {
    TextTweet replyTo;
    String contents;
    int likes;

    ReplyTweet(TextTweet replyTo, String contents, int likes) {
        this.replyTo = replyTo;
        this.contents = contents;
        this.likes = likes;
    }
    
    boolean morePopularReply() {
        return this.likes > replyTo.likes;
    }

    int allLikes() {
        return this.likes + replyTo.likes;
    }

    boolean hasMention(String username) {
        return replyTo.hasMention(username) ||
                this.contents.contains("@" + username + " ") || 
                this.contents.endsWith("@" + username);
    }
}

class Drill3 { 
    boolean testHasLike(Tester t) {
        TextTweet t1 = new TextTweet("@neolee1dsakjd @neolee ", 5);
        TextTweet t2 = new TextTweet("@neolee1dsakjd @neolee ", 0);
        return t.checkExpect(t1.hasLike(), true) &&
                t.checkExpect(t2.hasLike(), false);
    }

    boolean testHasMention(Tester t) {
        TextTweet t1 = new TextTweet("@neolee1dsakjd @neolee ", 5);
        TextTweet t2 = new TextTweet("@neolee1dsakjd @neolee", 5);
        return t.checkExpect(t1.hasMention("neolee"), true) &&
                t.checkExpect(t2.hasMention("neolee"), true);
    }

    boolean testFirstMention(Tester t) {
        TextTweet t1 = new TextTweet("@fnsdkj", 0);
        TextTweet t2 = new TextTweet("@fnsdkj ", 0);
        TextTweet t3 = new TextTweet("fdsfd @fnsdkj", 0);
        return t.checkExpect(t1.firstMention(), "") &&
                t.checkExpect(t2.firstMention(), "fnsdkj") &&
                t.checkExpect(t3.firstMention(), "");
    }

    boolean testReplyTweetHasMention(Tester t) {
        ReplyTweet r1 = new ReplyTweet(new TextTweet("@neo fdsfkjhrweou", 0), "fhweih ff@nel", 0);
        ReplyTweet r2 = new ReplyTweet(new TextTweet("fdfsfdvg", 0), "fhweih ff@neo", 0);
        ReplyTweet r3 = new ReplyTweet(new TextTweet("fdfsfdvg", 0), "fdsf@neo fweoifj", 0);
        return t.checkExpect(r1.hasMention("neo"), true) &&
                t.checkExpect(r2.hasMention("neo"), true) &&
                t.checkExpect(r3.hasMention("neo"), true);
    }

}