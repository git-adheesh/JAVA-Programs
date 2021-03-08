package CSP358;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

class Video
{
	String title;
	boolean checked; //a flag to say whether it is checked out or not; 
	float rating; //An average user rating
	
	public Video() {
		// TODO Auto-generated constructor stub
		this.checked = false;
	}
	public Video(String name) {
		// TODO Auto-generated constructor stub
	this.title = name;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	void beingCheckedOut()
	{
		checked = true;
		System.out.println("Video "+getTitle()+" checked out succesfully");
	}
	
	void beingReturned(boolean set)
	{
		checked = set;  //checked--> OUT: true  --------  IN: false
		System.out.println("Video "+getTitle()+" has been returned succesfully");
	}
	
	public void recievingRating(float rating)
	{
		this.rating = rating;
	}
	
	public float getRating()
	{
		return rating;
	}
	
	public boolean checkedStatus()
	{
		return checked;
	}

}


class VideoStore extends Video
{
	ArrayList<String> arrVideo = new ArrayList<String>();
	ArrayList<String> totalVideo = new ArrayList<String>();
	ArrayList<Integer> videoNumber = new ArrayList<Integer>();
	
	int startingVideoNumber  = 1001;
	
	//ArrayList<Integer> uniqueVideoRating = new ArrayList<Integer>();
	
	ArrayList<ArrayList<Float> > userRating = new ArrayList<ArrayList<Float> >();

	int count = 0;
	int totalUniqueVideo = 0;
	
	void addVideo(String addVideo)
	{	
		arrVideo.add(count++, addVideo);
		videoNumber.add(startingVideoNumber++);
		boolean flag = totalVideo.contains(addVideo);
		if(flag == false)
		{
			totalVideo.add(addVideo);
			userRating.add(new ArrayList<Float>());
			totalUniqueVideo++;
		}
	}
	
	void checkout(int videoNum)
	{
			int index = videoNum - 1001;
			arrVideo.remove(index);
			count--;
			startingVideoNumber--;
	}
	
	void returnVideo(String retVideo)
	{
		arrVideo.add(count++, retVideo);
		startingVideoNumber++;
	}
	
	void defaultRatings(String videoName, float rating)
	{
		int pos = totalVideo.indexOf(videoName);
		if(pos>= 0) 
		userRating.get(pos).add(rating);
	}
	
	float videoAvgRating(String videoName)
	{
		int pos = totalVideo.indexOf(videoName);
		float sumRating = 0;
		
		for(int i = 0; i < userRating.get(pos).size(); i++)
		{
			sumRating = sumRating + userRating.get(pos).get(i);
		}
		
		float avg = sumRating/userRating.get(pos).size();
		return avg;
	}
	
	void listInventory()
	{
		System.out.println();
		System.out.println("Video available in inventory is/are: ");
		System.out.println();
		System.out.println("VIDEO NO.\tVIDEO TITLE\t\t\t\t RATING \tPAST RATING(S)");
		System.out.println();
		
		//just to insert space or in coding we say set precision to our output
		int max = 0;
		for(int j  = 0; j<arrVideo.size(); j++)
		{
			if(arrVideo.get(j).length() >= max )
				max = arrVideo.get(j).length();
		}
		
		for(int i = 0; i < arrVideo.size(); i++ )
		{
			int c = totalVideo.indexOf(arrVideo.get(i));
			System.out.print("  "+videoNumber.get(i) + "\t\t"+ arrVideo.get(i));//+"\t\t\t"+userRating.get(c));
			
			int space = max - arrVideo.get(i).length() + 10;
			//just to insert space or in coding we say set precision to our output
			while(space!=0)
			{
				System.out.print(" ");
			space--;	
			}
			System.out.print(String.format("%.2f", videoAvgRating(arrVideo.get(i)))+"\t\t"+userRating.get(c));
			System.out.println();
		}
	}
	
}

public class VideoStoreLauncher {

	public static void main(String[] args) throws IOException{
		
		VideoStore obj = new VideoStore();
		obj.addVideo("Matrix");
		obj.addVideo("Godfather II");
		obj.addVideo("Star Wars Episode IV: A New Hope");
		obj.addVideo("Stranger Things");
		obj.addVideo("Sherlock Holmes");
		
		//Rating given out of 10
		obj.defaultRatings("Matrix", 8);
		obj.defaultRatings("Godfather II",9);
		obj.defaultRatings("Star Wars Episode IV: A New Hope", 7);
		obj.defaultRatings("Stranger Things", 8.5f);
		obj.defaultRatings("Sherlock Holmes", 9);
		obj.defaultRatings("Stranger Things", 7.8f);
		
		Scanner s = new Scanner(System.in);
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		System.out.println("********************   WELCOME TO VIDEO RENTAL INVENTORY SYSTEM   ********************");
		//this is our initial inventory
		obj.listInventory();
		
		boolean program = true;
		
		while(program) 
		{
		System.out.println();
		System.out.println("ENter 1 to Rent a Video. \nEnter 2 to Return a Video & Give Rating. \nEnter 3 to Add More Videos.\nEnter 4 to Get Back to our Inventory. \nEnter 5 to Exit. ");
		int input = s.nextInt();

		switch(input)
		{
		case 1: System.out.println("Enter the VIDEO NO. of video to rent it: ");
				boolean f = true;
				while(f) 
				{
						int userInput = s.nextInt();
						boolean check = obj.videoNumber.contains(userInput);
						if(check)
						{
							f = false;
							int index = userInput - 1001;
							String videoTitle = obj.arrVideo.get(index);
							obj.checkout(userInput);
							Video v = new Video(videoTitle);
							v.beingCheckedOut();
							obj.listInventory();
						}
						else
						{
							System.out.println("SORRY! Video is not available");
							System.out.println("Please enter VALID VIDEO NUMBER again! ");
						}
						System.out.println();
				}
			break;
			
		case 2: System.out.println("Enter the VIDEO TITLE to return it: ");
				String returnInput = sc.readLine();
				Video v = new Video(returnInput);
				obj.returnVideo(returnInput);
				v.beingReturned(false);
				obj.listInventory();
				System.out.println();
			    System.out.println("Enter the rating for Returned Video within range 0 to 10:  ");
				float userRated = s.nextFloat();
				obj.recievingRating(userRated);
				obj.defaultRatings(v.getTitle(),obj.getRating());
				System.out.println("THANKS! for your Valuable Feedback");
				obj.listInventory();
				System.out.println();
			break;	
		
		case 3: boolean add = true;
				while(add)
				{	
					System.out.println("Enter the VIDEO TITLE to Add Video in Inventory: ");
					String n = sc.readLine();
					System.out.println("Enter its default rating: ");
					float r = s.nextFloat();
					obj.addVideo(n);
					obj.defaultRatings(n, r);
					System.out.println("Video Added Successfully!");
					obj.listInventory();
					System.out.println();
					System.out.println("Do you want to ADD MORE VIDEO(s)...(y/n)");
					char ch = s.next().charAt(0);
					if(ch == 'y')
						add = true;
					else {
						add = false;
						System.out.println("Video Addition Completed");
					}
				}
			break;
			
		case 4: obj.listInventory();
			break;
			
		case 5: System.out.println("Thanks! VIDEO RENTAL INVENTORY SYSTEM Closed Successfully."); 
			System.exit(0);
			break;
			
		default: System.out.println("Enter Correct Input!");
		}
		}
		s.close();
		sc.close();
	}
}
