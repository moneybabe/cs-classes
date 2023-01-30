class R {
    String field1;
    R field2;

    R(String field1, R field2) {
        this.field1 = field1;
        this.field2 = field2;
    }
}

class ExamplesR {
    /* 
    I was not able to construct and example R object
    because I have no instance from class R to create the first R instance
     */

     /* 
    Yes I can construct an example of a reply to a reply to a Tweet with the class structur in Drill 3
    The way I would implement it:
    1. Assign a new unintialized field (prevTweet) that point to the previous tweet in ReplyTweet (not required in constructor)
    2. Create a setter method for prevTweet
    3. Create the head node (an instance of TextTweet)
    4. Create an instance of ReplyTweet with the head node
    5. For any new tweets in the thread, create a new instance of ReplyTweet with the head node
    6. Set the prevTweet of the new tweets to the previous tweet node
    7. Which is essentially implementing a linked list
    */
}