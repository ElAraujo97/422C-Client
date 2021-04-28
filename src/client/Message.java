package client;


class Message {
  String item;
  Integer startBid;
  Integer buyBid;
  String highestBidder;
  Integer time;
  Boolean sold;
  String action;

  protected Message() {
    this.item = "No item";
    this.startBid = 0;
    this.buyBid = 1;
    this.highestBidder = "No name";
    this.time = 0;
    this.sold = false;
    this.action = "Setup";
    System.out.println("client-side message created");
  }

  protected Message(String item, int start, int buy, String bidder, int time, Boolean sold, String action) {
	  this.item = item;
	  this.startBid = start;
	  this.buyBid = buy;
	  this.highestBidder = bidder;
	  this.time = time;
	  this.sold = false;
	  this.action = "Setup";
	  System.out.println("client-side message created");
    
    
  }
  
  protected void setItem(String myItem) {
	  this.item = myItem;
  }
  
  protected void setStartBid(int start) {
	  this.startBid = start;
  }
  
  protected void setBuyBid(int buy) {
	  this.buyBid = buy;
  }
  
  protected void setHighestBidder(String name) {
	  this.highestBidder = name;
  }
  
  protected void setSold() {
	  this.sold = true;
  }
  
  
  protected String getItem() {
	  return this.item;
  }
  
  protected Integer getStartBid() {
	  return this.startBid;
  }
  
  protected Integer getBuyBid() {
	  return this.buyBid;
  }
  
  protected String getHighestBidder() {
	  return this.highestBidder;
  }
  
  protected Boolean getSold() {
	  return this.sold;
  }

  protected void setTime(int time) {
	// TODO Auto-generated method stub
	this.time = time;
}
  
  protected Integer getTime() {
	// TODO Auto-generated method stub
	return this.time;
}
  
  protected void setAction(String action) {
	// TODO Auto-generated method stub
	this.action = action;
}
  
  protected String getAction() {
	// TODO Auto-generated method stub
	return this.action;
}
  
  
  
}