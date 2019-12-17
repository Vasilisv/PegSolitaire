


public class Peg implements Comparable<Peg>{
	public static int UP_DOWN = 0;
	public static int DOWN_UP = 1;
	public static int LEFT_RIGHT = 2;
	public static int RIGHT_LEFT = 3;
	static int N=Game.N, M=Game.M;
	
	int[][] data;
	int heuristic;
	
	Peg(){
		this.data = Game.data;
	}
	
	Peg(int heuristic){
		this.data = Game.data;
		this.heuristic=heuristic;
	}
	
	
	
	public static Peg goal(){
		Peg p = new Peg();
		
		for(int i=0;i<=N-1;i++)
			for(int j=0;j<=M-1;j++)
				p.data[i][j]=2;
		
		for(int i=0;i<=1;i++)
			for(int j=0;j<=1;j++)
				p.data[i][j]=-1;
		
		
		
		p.data[3][3]=1;	
		
		return p;
	}

	public boolean isGoal(){
		boolean result = true;
		Peg goal = Peg.goal();
		for(int i=0;i<=N;i++){
			for(int j=0;j<=M;j++){
				if(this.data[i][j] != goal.data[i][j])
					result=false;				
			}
		}
			return result;
	}
	
	public static void copy(Peg p, Peg q){
		for(int i=0; i<q.data.length; i++)
			  for(int j=0; j<q.data[i].length; j++)
			    q.data[i][j]=p.data[i][j];
	}

	@Override
	public int compareTo(Peg o) {
			return this.heuristic - o.heuristic;		
	}
	
	//when a movement is done, it leaves 2 blank holes in the way. This heuristic
	//analyzes the amount of movement between these 2 blank holes.
	public static int firstHeuristic(Peg q,int i,int j,int typeMov){
		int i1 = 0,i2=0,j1=0,j2=0;
		if(typeMov == UP_DOWN){
			i1=i-1;
			j1=j;
			
			i2=i-2;
			j2=j;
		}else if(typeMov == DOWN_UP){
			i1=i+1;
			j1=j;
			
			i2=i+2;
			j2=j;
		}else if(typeMov == LEFT_RIGHT){
			i1=i;
			j1=j-1;
			
			i2=i;
			j2=j-2;
		}else if(typeMov == RIGHT_LEFT){
			i1=i;
			j1=j+1;
			
			i2=i;
			j2=j+2;
		}
		
		int h=0;
		
		try { //up-down
			if(q.data[i1-1][j1]==1 && q.data[i1-2][j1]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {	}
		try {//down-up
			if(q.data[i1+1][j1]==1 && q.data[i1+2][j1]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {//left-right
			if(q.data[i1][j1-1]==1 && q.data[i1][j1-2]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {//right-left
			if(q.data[i1][j1+1]==1 && q.data[i1][j1+2]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if(q.data[i2-1][j2]==1 && q.data[i2-2][j2]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if(q.data[i2+1][j2]==1 && q.data[i2+2][j2]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if(q.data[i2][j2-1]==1 && q.data[i2][j2-2]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		try {
			if(q.data[i2][j2+1]==1 && q.data[i2][j2+2]==1) h++;
		} catch (ArrayIndexOutOfBoundsException e) {}
		
		return 8-h;
	}
}