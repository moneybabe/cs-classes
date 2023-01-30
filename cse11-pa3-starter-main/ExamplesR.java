class R {
    String field1;
    R field2;

    R(String field1, R field2) {
        this.field1 = field1;
        this.field2 = field2;
    }
}

class ExamplesR {

    R r = new R("gm", null);

    /* 
    Yes I created r with field1="gm", field2=null
    This will be the head node of the thread
     */

     /* 
    No, I cannot construct an example of a reply to a reply to a Tweet with the exact structure and fields from Drill 3
    Because the class ReplyTweet does not have a field that points to another instance of ReplyTweet
    I can add a field (ReplyTweet prevTweet) in ReplyTweet that points to the previous ReplyTweet instance to make it work
    */
}