package com.sampleapp.twitter;

import twitter4j.FilterQuery;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TwitterClient {
	public static void main(String[] args) {
		
		Twitter twitter = TwitterFactory.getSingleton();
		try {
			ResponseList<Status> list = twitter.getHomeTimeline();
			for(Status status:list){
				System.out.println(status.getText());
			}
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		
		TwitterStream stream = new TwitterStreamFactory().getInstance();
		StatusListener listerner = new StatusListener() {
			
			public void onException(Exception e) {
				System.out.println("Got Exception "+e.getMessage());
			}
			
			public void onTrackLimitationNotice(int arg0) {
				System.out.println("Tack Limitation notice triggered");
			}
			
			public void onStatus(Status arg0) {
				System.out.println("Got Status "+arg0.getText());				
			}
			
			public void onStallWarning(StallWarning arg0) {
				System.out.println("Stalled");				
			}
			
			public void onScrubGeo(long arg0, long arg1) {
				System.out.println("Not sure what ScrubGeo is");
				
			}
			
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				System.out.println("Deletion notice for "+arg0.getUserId());				
			}
		};
		
		FilterQuery qry = new FilterQuery();
		String[] identifiers = {"Feku","AAptard","Salman Khan"};
		qry.track(identifiers);
		
		stream.addListener(listerner);
		stream.filter(qry);
	}
}
