package so;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import so.memory.AddressMemory;

public class Process {
	private String id;
	private int sizeInMemory;

	private AddressMemory adressInMemory;
	
	
	
	public Process() {		
		
		this.id = UUID.randomUUID().toString();
	//	this.sizeInMemory = sizeInMemory;
	//	Random rand = new Random();
	//	List<Integer> givenlist = Arrays.asList(1, 2, 4, 5, 8, 10, 20, 50 ,100);
	//	this.sizeInMemory = givenlist.get(rand.nextInt(givenlist.size()));			
	}
	public Process(int sizeInMemory ) {
		this();
		this.sizeInMemory = sizeInMemory;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getSizeInMemory() {
		return sizeInMemory;
	}
	public void setSizeInMemory(int sizeInMemory) {
		this.sizeInMemory = sizeInMemory;
	}

	
	
}
