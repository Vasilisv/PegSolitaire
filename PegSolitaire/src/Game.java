import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Game {


	static int[][] data;
	static int[][] changesi;
	int heuristic;
	static int k = 0;
	static int N=0, M=0;
	static int iterations=0+1;
	
	
	public static void main(String [] args) {
		int i=0;

		String fileName = "C:\\Users\\Βασίλης\\Desktop\\input.txt";

		String line = null;

		try {
			FileReader fileReader = 
					new FileReader(fileName);
			BufferedReader bufferedReader = 
					new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				System.out.println(line);
				int j=0;
				//taking the size of the table
				if(i==0) {
					StringTokenizer st = new StringTokenizer(line);
					while (st.hasMoreTokens()){	
						N=M;
						String token= st.nextToken();
						String myString = token;
						M = Integer.parseInt(myString);
					}
					i=1;
				}


				if(i==1) {
					data = new int[N][M];
					line=bufferedReader.readLine();
					i=2;
				}


				//System.out.println(N);
				//System.out.println(M);

				StringTokenizer p = new StringTokenizer(line);

				while (p.hasMoreTokens())
				{
					String token= p.nextToken();
					String myString = token;
					int datas = Integer.parseInt(myString);
					data[k][j]=datas;
					j = j + 1;

				}   
				k= k + 1;

			}
			bufferedReader.close();   
		}

		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open file '" + 
							fileName + "'");                
		}

		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ fileName + "'");     
		}
		System.out.println(Arrays.deepToString(data));
		System.out.println(data[3][2]);
		
	}

	public static int DFS(Peg start) throws FileNotFoundException, UnsupportedEncodingException{
		System.out.println("asdasd");
	Stack<Peg> stack = new Stack<Peg>();
	
	stack.push(start);

	while(!stack.isEmpty()){
		Peg p = stack.pop();
		System.out.println(++iterations + "  " + stack.size());
		
		
		System.out.println(Arrays.deepToString(data));
			for(int i=0;i<N;i++){
				for(int j=0;j<M;j++){
					if(p.data[i][j] == 2){
						//if the tile is a hole, there is 4 possibilites of movement:
						//first, up-down
						try{
							if(p.data[i-1][j]==1 && p.data[i-2][j]==1) {
								Peg q = new Peg();
								Peg.copy(p,q);
								changes[][]=
								q.data[i-2][j]=2;
								q.data[i-1][j]=2;
								q.data[i][j]=1;
								stack.push(q);
								System.out.println("metakinaw to:" + i + j);
							}
						}
						catch(ArrayIndexOutOfBoundsException e){}
						try{
							//down-up
							if(p.data[i+1][j]==1 && p.data[i+2][j]==1){
								Peg q = new Peg();
								Peg.copy(p,q);
								
								q.data[i+2][j]=2;
								q.data[i+1][j]=2;
								q.data[i][j]=1;
								stack.push(q);
								System.out.println("metakinaw to:" +i + j);
							}
						}
						catch(ArrayIndexOutOfBoundsException e){}
						try{
							//left-right
							if(p.data[i][j-1]==1 && p.data[i][j-2]==1){
								Peg q = new Peg();
								Peg.copy(p,q);
								q.data[i][j-1]=2;
								q.data[i][j-2]=2;
								q.data[i][j]=1;
								stack.push(q);
								System.out.println("metakinaw to:" +q.data[i][j]);
							}
						}
						catch(ArrayIndexOutOfBoundsException e){}
						try{
							//right-left
							if(p.data[i][j+1]==1 && p.data[i][j+2]==1){
								Peg q = new Peg();
								Peg.copy(p,q);
								q.data[i][j+1]=2;
								q.data[i][j+2]=2;
								q.data[i][j]=1;
								stack.push(q);
								System.out.println("metakinaw to:" +q.data[i][j]);
							}
						}catch(ArrayIndexOutOfBoundsException e){}
					}
				}
			}
	}
	PrintWriter writer = new PrintWriter("solution.txt", "UTF-8");
	writer.println(+iterations);
	writer.println("The second line");
	writer.close();
			System.out.println(Arrays.deepToString(data));
	return 0;
	}



	
}

