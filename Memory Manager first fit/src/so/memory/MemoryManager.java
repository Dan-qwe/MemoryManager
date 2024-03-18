package so.memory;
import java.util.Hashtable;
import java.util.List;

import so.Process;
public class MemoryManager {
	
	private Strategy strategy;
	private int pageSize;
	private String[] physicMemory;
	private Hashtable<String,List<FrameMemory>> logicalMemory;
	
	public MemoryManager(Strategy strategy, int pageSize){
		this.strategy = strategy;
		this.physicMemory = new String[128];
		this.logicalMemory = new Hashtable<>();
		this.pageSize = pageSize;
		 
	}
	public MemoryManager(Strategy strategy){
		this(strategy, 2);
		
	}

	public void writeProcess(Process p) {
		if(this.strategy.equals(strategy.FIRST_FIT)) {
			this.writingUsingFirstFit(p);
		}
		else if(this.strategy.equals(strategy.BEST_FIT)) {
			this.writingUsingBestFit(p);
		}
		else if(this.strategy.equals(strategy.WORST_FIT)) {
			this.writingUsingWorstFit(p);
		}
		else if(this.strategy.equals(strategy.PAGING)) {
			this.writingUsingPaging(p);
		}
	}
		
		

	private void writingUsingPaging(Process p) {
		// TODO Auto-generated method stub
		
	}

	private void writingUsingWorstFit(Process p) {
		// TODO Auto-generated method stub
		
	}

	private void writingUsingBestFit(Process p) {
		// TODO Auto-generated method stub
		
	}

	private void writingUsingFirstFit(Process p) {
		
		int actualSize = 0;
		for(int i = 0; i< physicMemory.length; i++) {
			
			if(i == (physicMemory.length - 1)) {
				if (actualSize> 0) {
					
					if(p.getSizeInMemory()<=actualSize) {
						int start = i - actualSize;
						int end = i;
						for(int j = start;j < (start + p.getSizeInMemory());j++) {
							physicMemory[j] = p.getId();
						}
						break;
					}
					actualSize = 0;
				}
			}
			
			else if(physicMemory[i] == null) {
				actualSize++;
			}
			else {
				if (actualSize> 0) {
					
					if(p.getSizeInMemory()<=actualSize) {
						int start = i - actualSize;
						//int end = i -1;
						for(int j = start; j < (start + p.getSizeInMemory());j++) {
							physicMemory[j] = p.getId();
						}
						break;
					}
					actualSize = 0;
				}
			}
		}
		printStatusMemory();
	}

	private void printStatusMemory() {
		for(int i = 0; i<physicMemory.length; i++){
			System.out.print(physicMemory[i] + "|");
			
		}
	}

	public void deleteProcess() {
		// TODO Auto-generated method stub
		
	}
}