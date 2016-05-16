package mazeGenerator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import maze.Cell;
import maze.Maze;

public class RecursiveBacktrackerGenerator implements MazeGenerator {

	int sizeC;
	int sizeR;
	int deltaR[];
	int deltaC[];

	boolean visited;
	
	boolean marked;
	
	public boolean isMarked(){
		return marked;
	}

	protected boolean isIn(Cell cell) {
		if (cell == null)
			return false;
		return isIn(cell.r, cell.c);
	} // end of isIn()

	protected boolean isIn(int r, int c) {
		return r >= 0 && r < sizeR && c >= 0 && c < sizeC;
	} // end of isIn()

	@Override
	public void generateMaze(Maze maze) {
		this.sizeC = maze.sizeC;
		this.sizeR = maze.sizeR;
		this.deltaR = Maze.deltaR;
		this.deltaC = Maze.deltaC;

		Random random = new Random();
		int r = random.nextInt(sizeR);
		int c = random.nextInt(sizeC);
		int dir[] = { Maze.NORTH, Maze.EAST, Maze.SOUTH, Maze.WEST };
		
		
		ArrayList<Cell> visited = new ArrayList<Cell>();

		LinkedList<Cell> stack = new LinkedList<Cell>();

		Cell cell = new Cell(r, c);
		cell = maze.map[r][c];
		

		

		boolean[][] marked = new boolean[sizeR][sizeC];
		for (int i = 0; i < sizeR; i++) {
			for (int j = 0; j < sizeC; j++) {
				marked[i][j] = false;
			}
		}

		// Cell neigh = maze.map[r + deltaR[random.nextInt(dir.length)]][c +
		// deltaC[random.nextInt(dir.length)]];
		// cell = neigh;

		stack.addFirst(cell);
		marked[r][c] = true;
		int[] neighbours = new int[4];

		do {
			System.out.println(cell.r + "    " + cell.c);



			// Examine the current cell's neighbors
			int freeNeighbourCount = 0;
			for (int i = 0; i < dir.length; i++) {
//				Cell tmp = maze.map[cell.r][cell.c].neigh[i];
//				Cell tmp = maze.map[cell.r + deltaR[i]][cell.c + deltaC[i]];
				
				switch (i) {
				case 0:
					//up
					if (cell.r < sizeR && isIn(cell.r + deltaR[Maze.NORTH],cell.c + deltaC[Maze.NORTH])) {
//						Cell neigh = maze.map[r + deltaR[Maze.NORTH]][c + deltaC[Maze.NORTH]];
//						cell.neigh[i] = neigh;
//						Cell nextCell = cell.neigh[i];
						if (!marked[cell.r+deltaR[Maze.NORTH]][cell.c + deltaC[Maze.NORTH]]) {
							//visited.add(maze.map[r + deltaR[Maze.NORTH]][c + deltaC[Maze.NORTH]]);
							neighbours[freeNeighbourCount++] = i;
							System.out.println("free1");
						}
						// if (!marked[r + deltaR[Maze.NORTH]][c +
						// deltaC[Maze.NORTH]])

					}
					break;
				case 1:
					//right
					if (cell.c < sizeC && isIn(cell.r + deltaR[Maze.EAST],cell.c + deltaC[Maze.EAST])) {
//						Cell neigh = maze.map[r + deltaR[Maze.EAST]][c + deltaC[Maze.EAST]];
//						cell.neigh[i] = neigh;
//						Cell nextCell = cell.neigh[i];
						if (!marked[cell.r+deltaR[Maze.EAST]][cell.c + deltaC[Maze.EAST]]) {
							//visited.add(maze.map[r + deltaR[Maze.EAST]][c + deltaC[Maze.EAST]]);
							neighbours[freeNeighbourCount++] = i;
							System.out.println("free2");
						}
						// if ( !marked[r + deltaR[Maze.EAST]][c +
						// deltaC[Maze.EAST]])
					}
					break;
				case 2:
					//down
					if (cell.r > 0 && isIn(cell.r + deltaR[Maze.SOUTH],cell.c + deltaC[Maze.SOUTH])) {
//						Cell neigh = maze.map[r + deltaR[Maze.SOUTH]][c + deltaC[Maze.SOUTH]];
//						cell.neigh[i] = neigh;
//						Cell nextCell = cell.neigh[i];
						if (!marked[cell.r+deltaR[Maze.SOUTH]][cell.c + deltaC[Maze.SOUTH]]) {
							//visited.add(maze.map[r + deltaR[Maze.SOUTH]][c + deltaC[Maze.SOUTH]]);
							neighbours[freeNeighbourCount++] = i;
							System.out.println("free3");
						}
						// if (!marked[r + deltaR[Maze.SOUTH]][c +
						// deltaC[Maze.SOUTH]])
					}
					break;
				case 3:
					//left
					if (cell.c > 0 && isIn(cell.r + deltaR[Maze.WEST],cell.c + deltaC[Maze.WEST])) {
//						Cell neigh = maze.map[r + deltaR[Maze.WEST]][c + deltaC[Maze.WEST]];
//						cell.neigh[i] = neigh;
//						Cell nextCell = cell.neigh[i];
						if (!marked[cell.r + deltaR[Maze.WEST]][cell.c + deltaC[Maze.WEST]]) {
							//visited.add(maze.map[r + deltaR[Maze.WEST]][c + deltaC[Maze.WEST]]);
							neighbours[freeNeighbourCount++] = i;
							System.out.println("free4");
						}
					}
					break;
				}
			}
			// Pick a random free neighbour
			if (freeNeighbourCount > 0) {
				stack.addFirst(cell);
				cell = new Cell(cell.r,cell.c);
				switch (neighbours[random.nextInt(freeNeighbourCount)]) {
				case 0:
					cell.r++;
					marked[cell.r][cell.c] = true;
//					System.out.println(cell.r+"  " + cell.c);
					System.out.println("up r+1");
					break;
				case 1:
					cell.c++;
					marked[cell.r][cell.c] = true;
//					System.out.println(cell.r+"  " + cell.c);
					System.out.println("right c+1");
					break;
				case 2:
					cell.r--;
					marked[cell.r][cell.c] = true;
//					System.out.println(cell.r+"  " + cell.c);
					System.out.println("down r-1");
					break;
				case 3:
					cell.c--;
					marked[cell.r][cell.c] = true;
//					System.out.println(cell.r+"  " + cell.c);
					System.out.println("left c-1");
					break;
				}
			} else {
				cell = stack.removeFirst();
				System.out.println(cell.r+"  " + cell.c + "back");
			}
			 try {
			 Thread.sleep(500);
			 } catch (InterruptedException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
			 }

		} while (!stack.isEmpty());


	} // end of generateMaze()

} // end of class RecursiveBacktrackerGenerator

