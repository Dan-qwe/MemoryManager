package so;

public class Execute {
	public static void main(String[] args) {
	Process p1 = SystemOperation.SystemCall(SystemCallType.CREATE, null, 20);
	SystemOperation.SystemCall(SystemCallType.WRITE, p1, 0);
	
	Process p2 = SystemOperation.SystemCall(SystemCallType.CREATE, null, 38);
	SystemOperation.SystemCall(SystemCallType.WRITE, p2, 0);
	
	Process p3 = SystemOperation.SystemCall(SystemCallType.CREATE, null, 38);
	SystemOperation.SystemCall(SystemCallType.WRITE, p3, 0);
	
	Process p4 = SystemOperation.SystemCall(SystemCallType.CREATE, null, 20);
	SystemOperation.SystemCall(SystemCallType.WRITE, p4, 0);
	
	SystemOperation.SystemCall(SystemCallType.DELETE, p2, 0);
	
	Process p5 = SystemOperation.SystemCall(SystemCallType.CREATE, null, 40);
	SystemOperation.SystemCall(SystemCallType.WRITE, p5, 0);
	
	Process p6 = SystemOperation.SystemCall(SystemCallType.CREATE, null, 8);
	SystemOperation.SystemCall(SystemCallType.WRITE, p6, 0);
	
	}
}
