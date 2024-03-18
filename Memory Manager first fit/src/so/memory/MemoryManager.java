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
		int numPages = (int) Math.ceil((double) p.getSizeInMemory() / pageSize);
	    int pageCount = 0;
	    
	    for (int i = 0; i < physicMemory.length; i++) {
	        if (physicMemory[i] == null) {
	        	System.out.println("inicio");
	            physicMemory[i] = p.getId() + "-" + pageCount;
	            pageCount++;
	            if (pageCount == numPages) {
	            	System.out.println("antes do break");
	                break;
	            }
	        }
	    }
	    
	    if (pageCount < numPages) {
	        System.out.println("Não há espaço suficiente na memória para alocar o processo.");
	        // Aqui você pode implementar um tratamento para lidar com a falta de espaço.
	    }
	    
	    printStatusMemory();
		
	}

	private void writingUsingWorstFit(Process p) {
		int maxBlockSize = -1;
	    int worstFitIndex = -1;
	    
	    for (int i = 0; i < physicMemory.length; i++) {
	        int blockSize = 0;
	        for (int j = i; j < physicMemory.length; j++) {
	            if (physicMemory[j] == null) {
	                blockSize++;
	            } else {
	                break;
	            }
	        }
	        if (blockSize >= p.getSizeInMemory() && blockSize > maxBlockSize) {
	            maxBlockSize = blockSize;
	            worstFitIndex = i;
	        }
	    }
	    
	    if (worstFitIndex != -1) {
	        for (int i = worstFitIndex; i < worstFitIndex + p.getSizeInMemory(); i++) {
	            physicMemory[i] = p.getId();
	        }
	    } else {
	        System.out.println("Não há espaço suficiente na memória para alocar o processo.");
	    }
	    
	    printStatusMemory();
		
	}

	private void writingUsingBestFit(Process p) {

	    int minBlockSize = Integer.MAX_VALUE;
	    int bestFitIndex = -1;
	    
	    for (int i = 0; i < physicMemory.length; i++) {
	        int blockSize = 0;
	        for (int j = i; j < physicMemory.length; j++) {
	            if (physicMemory[j] == null) {
	                blockSize++;
	            } else {
	                break;
	            }
	        }
	        if (blockSize >= p.getSizeInMemory() && blockSize < minBlockSize) {
	            minBlockSize = blockSize;
	            bestFitIndex = i;
	        }
	    }
	    
	    if (bestFitIndex != -1) {
	        for (int i = bestFitIndex; i < bestFitIndex + p.getSizeInMemory(); i++) {
	            physicMemory[i] = p.getId();
	        }
	    } else {
	        System.out.println("Não há espaço suficiente na memória para alocar o processo.");
	    }
	    
	    printStatusMemory();
		
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

	public void deleteProcess(Process p) {

	    for (int i = 0; i < physicMemory.length; i++) {
	        if (physicMemory[i] != null && physicMemory[i].startsWith(p.getId())) {
	            physicMemory[i] = null;
	        }
	    }
	    
	    printStatusMemory();
		
	}
}