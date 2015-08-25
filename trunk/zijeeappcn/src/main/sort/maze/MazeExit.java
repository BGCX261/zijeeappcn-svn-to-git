package maze;

import java.util.Stack;
public class MazeExit {
	
	/**
	 * �򵥹��캯��
	 */
	public MazeExit() {
	};

	/**
	 * �ڲ���
	 *
	 */
	private class Cell {
		int x = 0; // ��Ԫ������
		int y = 0; // ��Ԫ������
		boolean visited = false; // �Ƿ���ʹ�
		char c = ' '; // ��ǽ('1')����ͨ·('0')����㵽�յ��·��('*')

		public Cell(int x, int y, char c, boolean visited) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.visited = visited;

		}

	}

	/**
	 * @param cells
	 *            �Թ���ά����
	 */
	private void printMaze(Cell[][] cells) {
		for (int x = 0; x < cells.length; x++) {
			for (int y = 0; y < cells[x].length; y++)
				System.out.print(cells[x][y].c);
			System.out.println();
		}
	}

	/**
	 * @param cell1
	 * @param cell2
	 * @return
	 */
	private boolean isAdjoinCell(Cell cell1, Cell cell2) {
		if (cell1.x == cell2.x && Math.abs(cell1.y - cell2.y) < 2)
			return true;
		if (cell1.y == cell2.y && Math.abs(cell1.x - cell2.x) < 2)
			return true;
		return false;
	}

	/**
	 * @param cell
	 * @return
	 */
	private boolean isValidWayCell(Cell cell) {
		return cell.c == '1' && !cell.visited;
	}

	/**
	 * @param maze
	 * @return
	 */
	private Cell[][] createMaze(char[][] maze) {
		Cell[][] cells = new Cell[maze.length][];
		for (int x = 0; x < maze.length; x++) {
			char[] row = maze[x];
			cells[x] = new Cell[row.length];
			for (int y = 0; y < row.length; y++)
				cells[x][y] = new Cell(x, y, maze[x][y], false);
		}
		return cells;
	}

	/**
	 * @param maze
	 *            �Թ�����
	 * @param sx
	 *            �Թ����X����
	 * @param sy
	 *            �Թ����y����
	 * @param ex
	 *            �Թ������X����
	 * @param ey
	 *            �Թ�����y����
	 */
	public void mazeExit(char[][] maze, int sx, int sy, int ex, int ey) {
		Cell[][] cells = createMaze(maze); // �������Թ� printMaze(cells); //��ӡ�Թ�
		Stack s = new Stack(); // �����ջ
		Cell startCell = cells[sx][sy]; // ���
		Cell endCell = cells[ex][ey]; // �յ�
		s.push(startCell); // �����ջ
		startCell.visited = true; // �������ѱ�����
		while (!s.isEmpty()) {
			Cell current = (Cell) s.peek();
			if (current == endCell) { // ·���ҵ�
				while (!s.isEmpty()) {
					Cell cell = (Cell) s.pop();// ��·���ؽ�·���ϵĵ�Ԫ��Ϊ*
					cell.c = '*';
					// ��ջ���� cell ���ڵĵ�Ԫ����·������ɲ��֣�����֮�⣬
					// ��ջ�л��м�¼��������δ��������̽���ĵ�Ԫ��
					// ��Щ��Ԫֱ�ӳ�ջ
					while (!s.isEmpty() && !isAdjoinCell((Cell) s.peek(), cell))
						s.pop();
				}
				System.out.println("�ҵ�����㵽�յ��·����");
				printMaze(cells);
				return;
			} else { // �����ǰλ�ò����յ�
				int x = current.x;
				int y = current.y;
				int count = 0;
				if (isValidWayCell(cells[x + 1][y])) { // ����
					s.push(cells[x + 1][y]);
					cells[x + 1][y].visited = true;
					count++;
				}
				if (isValidWayCell(cells[x][y + 1])) { // ����
					s.push(cells[x][y + 1]);
					cells[x][y + 1].visited = true;
					count++;
				}
				if (isValidWayCell(cells[x - 1][y])) { // ����
					s.push(cells[x - 1][y]);
					cells[x - 1][y].visited = true;
					count++;
				}
				if (isValidWayCell(cells[x][y - 1])) { // ����
					s.push(cells[x][y - 1]);
					cells[x][y - 1].visited = true;
					count++;
				}
				if (count == 0)
					s.pop();// ��������㣬��ջ
			}// end of if
		}// end of while

		System.out.println("û�д���㵽�յ��·����");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
}
