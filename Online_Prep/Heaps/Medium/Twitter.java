package Heaps.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

class Tweet {
    int order;
    int tweetId;
    int userId;

    public Tweet(int order, int tweetId, int userId) {
        this.order = order;
        this.tweetId = tweetId;
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Tweet))
            return false;
        Tweet tweet = (Tweet) o;
        return tweetId == tweet.tweetId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(tweetId);
    }
}

class User {
    int userId;
    PriorityQueue<Tweet> tweets;
    Map<Integer, Integer> followerIds;
    Map<Integer, Integer> followingIds;

    public User() {
        this.tweets = new PriorityQueue<>((a, b) -> Integer.compare(b.order, a.order));
        this.followerIds = new HashMap<>();
        this.followingIds = new HashMap<>();
    }
}

public class Twitter {

    private int order = 0;

    Map<Integer, User> users;

    public Twitter() {
        users = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        if (users.get(userId) == null) {
            users.put(userId, new User());
        }

        User user = users.get(userId);
        Tweet tweet = new Tweet(order++, tweetId, userId);
        user.tweets.offer(tweet);
        user.userId = userId;
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new ArrayList<>();
        if (!users.containsKey(userId)) {
            return newsFeed;
        }

        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.order, a.order));
        Set<Integer> sources = new HashSet<>(users.get(userId).followingIds.keySet());
        sources.add(userId);

        for (int uid : sources) {
            if (users.containsKey(uid)) {
                for (Tweet tweet : users.get(uid).tweets) {
                    maxHeap.offer(tweet);
                }
            }
        }

        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            newsFeed.add(maxHeap.poll().tweetId);
            count++;
        }
        return newsFeed;
    }

    public void follow(int followerId, int followeeId) {
        users.putIfAbsent(followerId, new User());
        users.putIfAbsent(followeeId, new User());

        User follower = users.get(followerId);
        User followee = users.get(followeeId);

        followee.followerIds.put(followerId, followerId);
        follower.followingIds.put(followeeId, followeeId);
        // No tweet copying here
    }

    public void unfollow(int followerId, int followeeId) {
        if (!users.containsKey(followerId) || !users.containsKey(followeeId))
            return;

        User follower = users.get(followerId);
        User followee = users.get(followeeId);

        followee.followerIds.remove(followerId);
        follower.followingIds.remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */