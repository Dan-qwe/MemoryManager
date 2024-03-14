package so;

import so.cpu.CpuManager;
import so.memory.MemoryManager;
import so.memory.Strategy;
import so.schedule.Schedule;


public class SystemOperation {
	private static MemoryManager mm;
	private static CpuManager cm;
	private static Schedule schedule;
		

	public static Process SystemCall(SystemCallType type, Process p, int pageSize) {
		if(type.equals(SystemCallType.WRITE)) {
			mm.writeProcess(p);
		}
		
		else if(type.equals(SystemCallType.DELETE)) {
			mm.deleteProcess();
		}
		
		else if(type.equals(SystemCallType.CREATE)) {
			if(cm == null) {
				cm = new CpuManager();
			}
			if(mm == null) {
				mm = new MemoryManager(Strategy.FIRST_FIT);
			}
			return new Process(pageSize);
		}
		return null;
		
	}
}













